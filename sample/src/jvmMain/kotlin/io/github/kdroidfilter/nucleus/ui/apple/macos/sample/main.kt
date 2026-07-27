package io.github.kdroidfilter.nucleus.ui.apple.macos.sample

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import dev.nucleusframework.application.nucleusApplication
import io.github.kdroidfilter.nucleus.ui.apple.macos.window.MacosWindow

fun main() = nucleusApplication {
    MacosWindow(
        onCloseRequest = ::exitApplication,
        title = "macosui",
    ) {
        val density = LocalDensity.current
        CompositionLocalProvider(LocalDensity provides Density(density.density * 1f, density.fontScale)) {
            App()
        }
    }
}
