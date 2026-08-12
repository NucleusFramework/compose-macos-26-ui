package dev.nucleusframework.macoscompose.icons

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Describes an icon with a platform-native SF Symbol name and a Lucide fallback.
 *
 * On macOS JVM, the SF Symbol is loaded natively via AppKit.
 * On all other platforms, the [fallback] Lucide vector is used.
 */
data class SystemIcon(
    val sfSymbolName: String,
    val fallback: ImageVector,
) {
    constructor(fallback: ImageVector) : this("", fallback)
}

/**
 * Whether [name] resolves to a native platform symbol on this device.
 *
 * Cheap and cached — it does not rasterize the symbol, so it can be called
 * during composition to decide between the native symbol and the fallback vector.
 */
expect fun platformSymbolExists(name: String): Boolean

/**
 * Rasterizes the platform symbol [name] so that its largest side is about
 * [sizePx] device pixels, or null when the symbol is unavailable.
 *
 * Rendering at the size the icon is actually drawn at keeps strokes crisp:
 * a symbol rasterized once at a fixed size has to be resampled by the drawing
 * code, which softens thin strokes on high-density displays.
 *
 * Results are cached per name and size.
 */
expect fun loadPlatformSymbol(name: String, sizePx: Int): ImageBitmap?
