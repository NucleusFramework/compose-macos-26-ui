package dev.nucleusframework.macoscompose.icons

import androidx.compose.ui.graphics.ImageBitmap

actual fun platformSymbolExists(name: String): Boolean = false

actual fun loadPlatformSymbol(name: String, sizePx: Int): ImageBitmap? = null
