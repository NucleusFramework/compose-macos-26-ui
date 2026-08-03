package dev.nucleusframework.macoscompose.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import dev.nucleusframework.macoscompose.util.isApplePlatform

@Composable
actual fun MacosFontFamily(): FontFamily =
    if (isApplePlatform) FontFamily.Default else ManropeFontFamily()
