package dev.nucleusframework.macoscompose.icons

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.NativeLibrary
import com.sun.jna.Pointer
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image
import java.util.concurrent.ConcurrentHashMap

private const val MIN_SIZE_PX = 4
private const val MAX_SIZE_PX = 1024
private const val MAX_FIT_PASSES = 4

internal object SFSymbolLoader {
    private val cache = ConcurrentHashMap<String, ImageBitmap>()
    private val existing = ConcurrentHashMap<String, Boolean>()

    fun exists(name: String): Boolean {
        if (name.isEmpty()) return false
        return existing.getOrPut(name) { bridge?.symbolExists(name) == true }
    }

    fun get(name: String, sizePx: Int): ImageBitmap? {
        if (!exists(name)) return null
        val size = sizePx.coerceIn(MIN_SIZE_PX, MAX_SIZE_PX)
        val key = "$name@$size"
        cache[key]?.let { return it }
        val bitmap = bridge?.loadSymbol(name, size) ?: return null
        cache[key] = bitmap
        return bitmap
    }

    private val bridge: ObjCBridge? by lazy {
        try {
            ObjCBridge()
        } catch (_: Exception) {
            null
        }
    }
}

/**
 * Typed JNA interface specifically for objc_msgSend calls that take double arguments.
 * On arm64, non-variadic doubles go in FP registers (correct behavior).
 */
private interface ObjCMsgSendDouble2 : Library {
    @Suppress("FunctionName")
    fun objc_msgSend(obj: Pointer, sel: Pointer, d1: Double, d2: Double): Pointer?

    companion object {
        val INSTANCE: ObjCMsgSendDouble2 = Native.load("objc", ObjCMsgSendDouble2::class.java)
    }
}

private class ObjCBridge {
    init {
        // AppKit must be loaded before NSImage can resolve SF Symbols
        NativeLibrary.getInstance("AppKit")
    }

    private val lib = NativeLibrary.getInstance("objc")
    private val fnGetClass = lib.getFunction("objc_getClass")
    private val fnSelRegister = lib.getFunction("sel_registerName")
    private val fnMsgSend = lib.getFunction("objc_msgSend")

    private fun cls(name: String): Pointer = fnGetClass.invokePointer(arrayOf(name))
    private fun sel(name: String): Pointer = fnSelRegister.invokePointer(arrayOf(name))

    private fun msg(obj: Pointer, sel: Pointer, vararg args: Any?): Pointer? =
        fnMsgSend.invokePointer(arrayOf(obj, sel, *args))

    private fun msgLong(obj: Pointer, sel: Pointer, vararg args: Any?): Long =
        fnMsgSend.invokeLong(arrayOf(obj, sel, *args))

    // Cached class lookups
    private val clsNSString = cls("NSString")
    private val clsNSImage = cls("NSImage")
    private val clsNSImageSymbolConfig = cls("NSImageSymbolConfiguration")
    private val clsNSBitmapImageRep = cls("NSBitmapImageRep")
    private val clsNSDictionary = cls("NSDictionary")
    private val clsNSAutoreleasePool = cls("NSAutoreleasePool")

    // Cached selectors
    private val selNew = sel("new")
    private val selDrain = sel("drain")
    private val selStringWithUTF8 = sel("stringWithUTF8String:")
    private val selImageWithSymbol = sel("imageWithSystemSymbolName:accessibilityDescription:")
    private val selConfigPointSize = sel("configurationWithPointSize:weight:")
    private val selWithConfig = sel("imageWithSymbolConfiguration:")
    private val selTIFF = sel("TIFFRepresentation")
    private val selRepWithData = sel("imageRepWithData:")
    private val selDictionary = sel("dictionary")
    private val selRepUsingType = sel("representationUsingType:properties:")
    private val selBytes = sel("bytes")
    private val selLength = sel("length")

    fun symbolExists(name: String): Boolean = withPool {
        val nsStr = msg(clsNSString, selStringWithUTF8, name) ?: return@withPool false
        msg(clsNSImage, selImageWithSymbol, nsStr, null) != null
    } ?: false

    /**
     * Rasterizes [name] so that its largest side fits [sizePx] pixels.
     *
     * A symbol's raster is larger than its point size by a ratio that depends on
     * the symbol, so the point size is corrected against the measured raster until
     * it fits — usually one correction, never more than [MAX_FIT_PASSES].
     */
    fun loadSymbol(name: String, sizePx: Int): ImageBitmap? = withPool {
        val nsStr = msg(clsNSString, selStringWithUTF8, name) ?: return@withPool null
        val image = msg(clsNSImage, selImageWithSymbol, nsStr, null) ?: return@withPool null

        var pointSize = sizePx.toDouble()
        var raster = rasterize(image, pointSize) ?: return@withPool null
        var pass = 1
        while (pass < MAX_FIT_PASSES) {
            val largestSide = maxOf(raster.width, raster.height)
            if (largestSide <= sizePx) break
            pointSize = pointSize * sizePx / largestSide
            raster = rasterize(image, pointSize) ?: break
            pass++
        }
        raster.toImageBitmap()
    }

    private fun rasterize(image: Pointer, pointSize: Double): Image? {
        // Configure point size via typed interface (avoids arm64 variadic double issue)
        val configured = runCatching {
            val config = ObjCMsgSendDouble2.INSTANCE.objc_msgSend(
                clsNSImageSymbolConfig, selConfigPointSize, pointSize, 0.0,
            )
            if (config != null) msg(image, selWithConfig, config) ?: image else image
        }.getOrDefault(image)

        // TIFF → NSBitmapImageRep → PNG
        val tiff = msg(configured, selTIFF) ?: return null
        val rep = msg(clsNSBitmapImageRep, selRepWithData, tiff) ?: return null
        val props = msg(clsNSDictionary, selDictionary) ?: return null
        // 4L = NSBitmapImageFileTypePNG
        val pngData = msg(rep, selRepUsingType, 4L, props) ?: return null

        val bytesPtr = msg(pngData, selBytes) ?: return null
        val length = msgLong(pngData, selLength)
        if (length <= 0) return null
        val pngBytes = bytesPtr.getByteArray(0, length.toInt())

        return Image.makeFromEncoded(pngBytes)
    }

    private fun Image.toImageBitmap(): ImageBitmap {
        val bitmap = Bitmap()
        bitmap.allocPixels(imageInfo)
        readPixels(bitmap, 0, 0)
        return bitmap.asComposeImageBitmap()
    }

    private fun <T> withPool(block: () -> T?): T? {
        val pool = msg(clsNSAutoreleasePool, selNew) ?: return null
        return try {
            block()
        } catch (_: Exception) {
            null
        } finally {
            msg(pool, selDrain)
        }
    }
}
