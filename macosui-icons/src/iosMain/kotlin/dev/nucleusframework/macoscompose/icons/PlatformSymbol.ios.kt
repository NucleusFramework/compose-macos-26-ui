package dev.nucleusframework.macoscompose.icons

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation
import platform.UIKit.UIImageSymbolConfiguration
import platform.Foundation.NSData
import platform.posix.memcpy

private const val MIN_SIZE_PX = 4
private const val MAX_SIZE_PX = 1024
private const val MAX_FIT_PASSES = 4

private val cache = mutableMapOf<String, ImageBitmap>()
private val existing = mutableMapOf<String, Boolean>()

actual fun platformSymbolExists(name: String): Boolean {
    if (name.isEmpty()) return false
    return existing.getOrPut(name) { UIImage.systemImageNamed(name) != null }
}

actual fun loadPlatformSymbol(name: String, sizePx: Int): ImageBitmap? {
    if (!platformSymbolExists(name)) return null
    val size = sizePx.coerceIn(MIN_SIZE_PX, MAX_SIZE_PX)
    val key = "$name@$size"
    cache[key]?.let { return it }

    // A symbol's raster is larger than its point size by a ratio that depends on the
    // symbol and on the screen scale, so correct the point size until the raster fits.
    var pointSize = size.toDouble()
    var raster = rasterize(name, pointSize) ?: return null
    var pass = 1
    while (pass < MAX_FIT_PASSES) {
        val largestSide = maxOf(raster.width, raster.height)
        if (largestSide <= size) break
        pointSize = pointSize * size / largestSide
        raster = rasterize(name, pointSize) ?: break
        pass++
    }

    val bitmap = raster.toImageBitmap()
    cache[key] = bitmap
    return bitmap
}

@OptIn(ExperimentalForeignApi::class)
private fun rasterize(name: String, pointSize: Double): Image? {
    val config = UIImageSymbolConfiguration.configurationWithPointSize(pointSize)
    val uiImage = UIImage.systemImageNamed(name, withConfiguration = config) ?: return null
    val pngData = UIImagePNGRepresentation(uiImage) ?: return null
    if (pngData.length.toInt() == 0) return null
    return Image.makeFromEncoded(pngData.toByteArray())
}

private fun Image.toImageBitmap(): ImageBitmap {
    val bitmap = Bitmap()
    bitmap.allocPixels(imageInfo)
    readPixels(bitmap, 0, 0)
    return bitmap.asComposeImageBitmap()
}

@OptIn(ExperimentalForeignApi::class)
private fun NSData.toByteArray(): ByteArray {
    val size = length.toInt()
    val bytes = ByteArray(size)
    bytes.usePinned { pinned ->
        memcpy(pinned.addressOf(0), this.bytes, length)
    }
    return bytes
}
