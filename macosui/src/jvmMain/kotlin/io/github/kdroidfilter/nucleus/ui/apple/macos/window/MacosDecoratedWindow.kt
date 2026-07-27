package io.github.kdroidfilter.nucleus.ui.apple.macos.window

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import dev.nucleusframework.application.DecoratedWindow
import dev.nucleusframework.application.NucleusApplicationScope
import dev.nucleusframework.application.NucleusDecoratedWindowScope
import dev.nucleusframework.window.LocalIsDarkTheme
import dev.nucleusframework.window.LocalWindowChromeInsets
import dev.nucleusframework.window.TitleBarPlacement
import dev.nucleusframework.window.WindowAppearance
import dev.nucleusframework.window.WindowAppearanceMode
import dev.nucleusframework.window.WindowControls
import dev.nucleusframework.window.WindowGlassRegionKind
import dev.nucleusframework.window.WindowScaffold
import dev.nucleusframework.window.macOSLargeCornerRadius
import dev.nucleusframework.window.noWindowDrag
import dev.nucleusframework.window.styling.LocalDecoratedWindowStyle
import dev.nucleusframework.window.windowDragArea
import dev.nucleusframework.window.windowGlassRegion
import io.github.kdroidfilter.nucleus.ui.apple.macos.components.LocalNativeWindowSync
import io.github.kdroidfilter.nucleus.ui.apple.macos.components.LocalNoWindowDragModifier
import io.github.kdroidfilter.nucleus.ui.apple.macos.components.LocalSidebarGlassRegionFactory
import io.github.kdroidfilter.nucleus.ui.apple.macos.components.LocalTitleBarRevalidate
import io.github.kdroidfilter.nucleus.ui.apple.macos.components.LocalWindowControlInset
import io.github.kdroidfilter.nucleus.ui.apple.macos.components.LocalWindowControlTrailingInset
import io.github.kdroidfilter.nucleus.ui.apple.macos.components.LocalWindowDragAreaModifier
import io.github.kdroidfilter.nucleus.ui.apple.macos.theme.LocalWindowActive
import io.github.kdroidfilter.nucleus.ui.apple.macos.util.isApplePlatform

/** Default title bar height matching TitleBarStyle.Unified. */
private val DefaultTitleBarHeight = 52.dp

/**
 * macOS-styled wrapper for Nucleus' `DecoratedWindow` — the macosui
 * counterpart of `JewelDecoratedWindow` / `MaterialDecoratedWindow`.
 *
 * It replicates the SwiftUI full-size content window style on top of the Tao
 * backend (no AWT, no bundled JNI):
 * 1. The window is `fullSizeContentView` with a transparent title bar — the
 *    Compose content fills the whole frame.
 * 2. The hidden `NSToolbar` is installed for the macOS 26 corner radius.
 * 3. Traffic-light buttons are recentered natively against [titleBarHeight]
 *    (published through Nucleus' `WindowScaffold` overlay slot); on Windows
 *    and Linux the platform window controls are drawn in that same band.
 * 4. Native surfaces (materials, traffic lights, window background) follow
 *    the `MacosTheme` rather than the OS appearance.
 *
 * The [Scaffold][io.github.kdroidfilter.nucleus.ui.apple.macos.components.Scaffold]
 * sidebar extends to the top of the window with the traffic lights floating
 * over its header area, exactly like SwiftUI's `NavigationSplitView`.
 *
 * Call it from `dev.nucleusframework.application.nucleusApplication`. The
 * [content] receives the window scope, so it can reach the window state and
 * the Nucleus chrome APIs (`WindowBackdrop`, `WindowAppearance`, …).
 */
@Suppress("FunctionNaming", "LongParameterList")
@Composable
fun NucleusApplicationScope.MacosDecoratedWindow(
    onCloseRequest: () -> Unit,
    state: WindowState = rememberWindowState(),
    visible: Boolean = true,
    title: String = "",
    icon: Painter? = null,
    resizable: Boolean = true,
    enabled: Boolean = true,
    focusable: Boolean = true,
    alwaysOnTop: Boolean = false,
    // Hide this window from the OS taskbar/Dock while it stays visible and
    // focusable (on Linux effective on X11/XWayland only).
    hiddenFromDock: Boolean = false,
    minimumSize: DpSize? = null,
    onPreviewKeyEvent: (KeyEvent) -> Boolean = { false },
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    titleBarHeight: Dp = DefaultTitleBarHeight,
    content: @Composable NucleusDecoratedWindowScope.() -> Unit,
) {
    // The MacosTheme lives inside [content], so it pushes its resolved
    // appearance up through LocalNativeWindowSync — reading LocalColorScheme
    // here would only ever see the defaults.
    var isDarkTheme by remember { mutableStateOf(false) }
    var background by remember { mutableStateOf(Color.Unspecified) }
    // Measured width of the platform window controls, published to the
    // Scaffold so its toolbar keeps clear of them (macOS: none, the AppKit
    // traffic lights live on the leading edge instead).
    var controlsWidth by remember { mutableStateOf(Dp.Unspecified) }
    val density = LocalDensity.current
    val nativeWindowSync: (Boolean, Color) -> Unit = { dark, bg ->
        isDarkTheme = dark
        background = bg
    }
    val baseWindowStyle = LocalDecoratedWindowStyle.current
    CompositionLocalProvider(
        LocalDecoratedWindowStyle provides
            baseWindowStyle.copy(colors = baseWindowStyle.colors.copy(background = background)),
    ) {
        DecoratedWindow(
            onCloseRequest = onCloseRequest,
            state = state,
            title = title,
            icon = icon,
            visible = visible,
            resizable = resizable,
            enabled = enabled,
            focusable = focusable,
            alwaysOnTop = alwaysOnTop,
            hiddenFromDock = hiddenFromDock,
            minimumSize = minimumSize,
            onPreviewKeyEvent = onPreviewKeyEvent,
            onKeyEvent = onKeyEvent,
        ) {
            val windowScope = this
            val windowActive = this.state.isActive
            // Native materials must follow the MacosTheme, not the OS setting:
            // a dark app on a light system would otherwise get a light sidebar
            // material under dark content. The value is pushed up from the
            // theme (see LocalNativeWindowSync).
            WindowAppearance(
                if (isDarkTheme) WindowAppearanceMode.Dark else WindowAppearanceMode.Light,
            )
            WindowScaffold(
                // macOS 26 large corner radius (hidden NSToolbar).
                modifier = Modifier.macOSLargeCornerRadius(),
                // Transparent strip: it publishes the title bar height to the
                // native layer (traffic-light centering / Windows caption
                // zone); the visible toolbar is drawn by the Scaffold. Nothing
                // in it consumes pointer events except the window controls, so
                // the content underneath stays reachable.
                titleBar = {
                    Box(Modifier.fillMaxWidth().height(titleBarHeight)) {
                        // macOS gets real AppKit traffic-lights; every other
                        // platform is fully undecorated and needs the buttons
                        // drawn here or the window cannot be closed.
                        if (!isApplePlatform) {
                            // Nucleus picks the glyph variant and the Fluent
                            // hover tint from LocalIsDarkTheme, which defaults
                            // to `true` — on a light MacosTheme that leaves a
                            // white-on-white hover. Drive it from the app theme.
                            CompositionLocalProvider(LocalIsDarkTheme provides isDarkTheme) {
                                WindowControls(
                                    Modifier
                                        .align(Alignment.TopEnd)
                                        // The strip's width varies with the
                                        // window state (a non-resizable window
                                        // drops the maximize button), so measure
                                        // it rather than assuming 3 x 46 dp.
                                        .onSizeChanged { size ->
                                            controlsWidth = with(density) { size.width.toDp() }
                                        },
                                )
                            }
                        }
                    }
                },
                titleBarPlacement = TitleBarPlacement.Overlay(autoHideInFullscreen = false),
            ) { _ ->
                val chromeInsets = LocalWindowChromeInsets.current
                val controlInset =
                    maxOf(
                        chromeInsets.controlsInsets.calculateLeftPadding(LayoutDirection.Ltr),
                        chromeInsets.controlsInsets.calculateRightPadding(LayoutDirection.Ltr),
                    )
                CompositionLocalProvider(
                    LocalWindowControlInset provides controlInset,
                    LocalWindowControlTrailingInset provides controlsWidth,
                    // Tao recenters the traffic lights reactively — no native
                    // constraint revalidation needed after relayouts.
                    LocalTitleBarRevalidate provides null,
                    LocalWindowActive provides windowActive,
                    LocalNativeWindowSync provides nativeWindowSync,
                    LocalWindowDragAreaModifier provides Modifier.windowDragArea(),
                    LocalNoWindowDragModifier provides Modifier.noWindowDrag(),
                    // macOS only: elsewhere the factory stays null so the
                    // Sidebar keeps its Compose-drawn material fallback.
                    LocalSidebarGlassRegionFactory provides
                        if (isApplePlatform) {
                            { cornerRadius ->
                                Modifier.windowGlassRegion(
                                    kind = WindowGlassRegionKind.Sidebar,
                                    cornerRadius = cornerRadius,
                                )
                            }
                        } else {
                            null
                        },
                ) {
                    windowScope.content()
                }
            }
        }
    }
}
