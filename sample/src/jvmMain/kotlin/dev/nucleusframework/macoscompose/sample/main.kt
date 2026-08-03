package dev.nucleusframework.macoscompose.sample

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import dev.nucleusframework.application.nucleusApplication
import dev.nucleusframework.macoscompose.window.MacosDecoratedWindow

fun main() = nucleusApplication {
    MacosDecoratedWindow(
        onCloseRequest = ::exitApplication,
        title = "macosui",
    ) {
        val density = LocalDensity.current
        CompositionLocalProvider(LocalDensity provides Density(density.density * 1f, density.fontScale)) {
            App()
        }
    }
}
