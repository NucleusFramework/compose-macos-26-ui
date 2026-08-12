package dev.nucleusframework.macoscompose.icons

import androidx.compose.ui.graphics.ImageBitmap

private val isApplePlatform: Boolean =
    System.getProperty("os.name").orEmpty().lowercase().let { "mac" in it || "darwin" in it }

actual fun platformSymbolExists(name: String): Boolean =
    isApplePlatform && SFSymbolLoader.exists(name)

actual fun loadPlatformSymbol(name: String, sizePx: Int): ImageBitmap? {
    if (!isApplePlatform) return null
    return SFSymbolLoader.get(name, sizePx)
}
