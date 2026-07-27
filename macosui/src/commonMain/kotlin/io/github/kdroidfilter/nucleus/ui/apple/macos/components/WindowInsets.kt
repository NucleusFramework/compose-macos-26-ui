package io.github.kdroidfilter.nucleus.ui.apple.macos.components

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Horizontal inset for the native window controls (traffic lights on macOS).
 *
 * When provided by a platform window composable (e.g. `MacosDecoratedWindow`), the
 * [Scaffold] uses this value to pad its built-in sidebar toggle so it doesn't
 * overlap with the native window controls.
 *
 * Defaults to [Dp.Unspecified] (no inset).
 */
val LocalWindowControlInset = compositionLocalOf { Dp.Unspecified }

/**
 * Callback to force re-application of native titlebar Auto Layout constraints.
 *
 * Provided by `MacosDecoratedWindow` when the JNI bridge is available. The [Scaffold]
 * calls this after sidebar show/hide animations complete to work around macOS
 * occasionally invalidating the titlebar view hierarchy during content relayout.
 *
 * Defaults to `null` (no-op on non-macOS platforms or when JNI is unavailable).
 */
val LocalTitleBarRevalidate = compositionLocalOf<(() -> Unit)?> { null }

/**
 * Modifier that turns a component into a native window drag region.
 *
 * Provided by the platform window composable (e.g. `MacosDecoratedWindow`, backed by
 * Nucleus' `Modifier.windowDragArea()`): press-and-move starts the native
 * interactive window move, double-click toggles maximize, and interactive
 * children opt out automatically by consuming the press. [TitleBar] applies
 * it to its root row so the toolbar drags the window out of the box.
 *
 * Defaults to [Modifier] (no-op) on platforms without window chrome.
 */
val LocalWindowDragAreaModifier = compositionLocalOf<Modifier> { Modifier }

/**
 * Factory for a modifier that renders the NATIVE wallpaper-tinted sidebar
 * material behind a component (Nucleus' `Modifier.windowGlassRegion`,
 * backed by a hosted `NSSplitViewController` — the exact System Settings
 * pattern: the desktop wallpaper shows through, windows behind never do).
 *
 * Provided by the platform window composable (e.g. `MacosDecoratedWindow`) with the
 * requested corner radius; `null` on platforms without native materials —
 * consumers then fall back to the Compose-drawn approximation
 * (`macosGlassMaterial`).
 */
val LocalSidebarGlassRegionFactory = compositionLocalOf<((Dp) -> Modifier)?> { null }

/**
 * Callback letting [MacosTheme][io.github.kdroidfilter.nucleus.ui.apple.macos.theme.MacosTheme]
 * push its resolved appearance up to the platform window composable.
 *
 * The theme is applied *inside* the window content, so the window cannot
 * read `LocalColorScheme` itself — it would see the defaults. Native
 * surfaces (glass materials, traffic lights, the window background) must
 * follow the app theme rather than the OS setting, hence this upward hop.
 *
 * Provided by `MacosDecoratedWindow`; `null` when there is no native window to sync.
 */
val LocalNativeWindowSync = compositionLocalOf<((isDark: Boolean, background: Color) -> Unit)?> { null }

/**
 * Horizontal inset for the window controls sitting on the TRAILING edge of
 * the title bar — the minimize / maximize / close buttons on Windows and
 * Linux, which Nucleus draws inside the Compose surface.
 *
 * Provided by the platform window composable (e.g. `MacosDecoratedWindow`) with the
 * measured width of the button strip, so [Scaffold] can keep its toolbar
 * clear of them. Stays [Dp.Unspecified] on macOS, where the controls are
 * native AppKit traffic lights on the leading edge (see
 * [LocalWindowControlInset]).
 */
val LocalWindowControlTrailingInset = compositionLocalOf { Dp.Unspecified }

