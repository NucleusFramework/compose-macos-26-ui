package dev.nucleusframework.macoscompose.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import dev.nucleusframework.darkmodedetector.isSystemInDarkMode
import dev.nucleusframework.systemcolor.systemAccentColor as nucleusSystemAccentColor

@Composable
internal actual fun BrowserNavigation(backStack: SnapshotStateList<AppNavKey>) = Unit

@Composable
internal actual fun isSystemDarkMode(): Boolean = isSystemInDarkMode()

@Composable
internal actual fun systemAccentRawColor(): Color? = nucleusSystemAccentColor()
