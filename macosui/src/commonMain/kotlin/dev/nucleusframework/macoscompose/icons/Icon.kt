package dev.nucleusframework.macoscompose.icons

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.theme.LocalContentColor
import kotlin.math.roundToInt

/** Size used when a [SystemIcon] is drawn without any size constraint. */
private val DefaultSystemIconSize = 16.dp

/**
 * Renders an [ImageVector] icon — mirrors Material3's Icon.
 *
 * @param imageVector The icon vector to render.
 * @param contentDescription Accessibility description (null for decorative icons).
 * @param modifier Modifier applied to the icon. Use [Modifier.size] to set icon size.
 * @param tint Tint color. Defaults to [LocalContentColor].
 */
@Composable
fun Icon(
    imageVector: ImageVector,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    val resolvedTint = if (tint == Color.Unspecified) Color.Black else tint
    Image(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier,
        colorFilter = ColorFilter.tint(resolvedTint),
    )
}

/**
 * Renders a [Painter] icon — mirrors Material3's Icon.
 */
@Composable
fun Icon(
    painter: Painter,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    val resolvedTint = if (tint == Color.Unspecified) Color.Black else tint
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        colorFilter = ColorFilter.tint(resolvedTint),
    )
}

/**
 * Renders an [ImageBitmap] icon — mirrors Material3's Icon.
 */
@Composable
fun Icon(
    bitmap: ImageBitmap,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    val resolvedTint = if (tint == Color.Unspecified) Color.Black else tint
    Image(
        bitmap = bitmap,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(resolvedTint),
    )
}

/**
 * Renders a [SystemIcon] — uses the native SF Symbol on macOS and iOS,
 * falls back to the bundled Lucide vector on all other platforms.
 */
@Composable
fun Icon(
    icon: SystemIcon,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    val symbolAvailable = remember(icon.sfSymbolName) { platformSymbolExists(icon.sfSymbolName) }
    if (!symbolAvailable) {
        Icon(imageVector = icon.fallback, contentDescription = contentDescription, modifier = modifier, tint = tint)
        return
    }
    val defaultSizePx = with(LocalDensity.current) { DefaultSystemIconSize.toPx() }
    val painter = remember(icon.sfSymbolName, defaultSizePx) {
        SystemSymbolPainter(icon.sfSymbolName, Size(defaultSizePx, defaultSizePx))
    }
    Icon(painter = painter, contentDescription = contentDescription, modifier = modifier, tint = tint)
}

/**
 * Draws a native platform symbol, rasterizing it at the pixel size it is drawn at.
 *
 * The raster is then blitted 1:1 onto the pixel grid: rasterizing once at a fixed
 * size and letting the drawing code resample it visibly softens thin strokes on
 * high-density displays.
 */
private class SystemSymbolPainter(
    private val symbolName: String,
    override val intrinsicSize: Size,
) : Painter() {
    private var alpha: Float = 1f
    private var colorFilter: ColorFilter? = null
    private var raster: ImageBitmap? = null
    private var rasterSizePx: Int = 0

    override fun applyAlpha(alpha: Float): Boolean {
        this.alpha = alpha
        return true
    }

    override fun applyColorFilter(colorFilter: ColorFilter?): Boolean {
        this.colorFilter = colorFilter
        return true
    }

    override fun DrawScope.onDraw() {
        val sizePx = maxOf(size.width, size.height).roundToInt()
        if (sizePx <= 0) return
        if (sizePx != rasterSizePx) {
            raster = loadPlatformSymbol(symbolName, sizePx)
            rasterSizePx = sizePx
        }
        val bitmap = raster ?: return
        drawImage(
            image = bitmap,
            dstOffset = IntOffset(
                x = ((size.width - bitmap.width) / 2f).roundToInt(),
                y = ((size.height - bitmap.height) / 2f).roundToInt(),
            ),
            dstSize = IntSize(bitmap.width, bitmap.height),
            alpha = alpha,
            colorFilter = colorFilter,
        )
    }
}
