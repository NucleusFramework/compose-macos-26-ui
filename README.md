# Compose Macos UI

A Compose Multiplatform adaptation of the native macos 26 style. This port brings the same design language to Android, iOS, Desktop, and Web through Compose Multiplatform.

[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.10-7F52FF.svg?logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.11.1-4285F4.svg?logo=jetpackcompose)](https://www.jetbrains.com/compose-multiplatform/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[Documentation & Live Demo](https://nucleusframework.github.io/compose-macos-26-ui/)

## Features

- **30+ components** — Buttons, Cards, Dialogs, Tables, Toasts, and more
- **Light & Dark mode** — Theme-aware styling with automatic system detection
- **macOS aesthetic** — Clean, native-feeling design inspired by Apple's design language
- **Compose Multiplatform** — Runs on Android, iOS, Desktop (JVM), and Web (JS/Wasm)
- **Decorated window** — Full-size content window with native traffic lights and glass materials on desktop
- **Built-in design system** — Colors, typography (Manrope), shapes, and animations out of the box
- **No external UI dependencies** — Pure Compose, no Material dependency required

## Supported Platforms

| Platform | Status |
|----------|--------|
| Android  | ✓      |
| iOS      | ✓      |
| Desktop (JVM) | ✓ |
| Web (JS) | ✓      |
| Web (Wasm) | ✓    |

## Artifacts

| Artifact | Contents |
|----------|----------|
| `dev.nucleusframework:compose-macos-ui` | Components, theme, decorated window |
| `dev.nucleusframework:compose-macos-ui-icons` | Core icon set (already exposed by `compose-macos-ui`) |
| `dev.nucleusframework:compose-macos-ui-icons-extended` | Full icon set |
| `dev.nucleusframework:compose-macos-ui-markdown` | macOS-styled Markdown renderer |

## Quick Start

### 1. Add the dependency

```kotlin
// build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("dev.nucleusframework:compose-macos-ui:<version>")
        }
    }
}
```

### 2. Wrap your app with MacosTheme

```kotlin
import dev.nucleusframework.macoscompose.theme.MacosTheme

@Composable
fun App() {
    MacosTheme(darkTheme = false) {
        // Your content here
        // Access design tokens via MacosTheme.colorScheme, MacosTheme.typography, etc.
    }
}
```

`MacosTheme` also accepts `accentColor` (blue, purple, violet, green, orange, red, yellow, cyan, pink,
teal, emerald, sky), `liquidGlass`, `glassType`, and full overrides for `colorScheme`, `typography`,
`shapes`, `animations`, and `componentStyling`.

### 3. Use components

```kotlin
import dev.nucleusframework.macoscompose.components.*

@Composable
fun MyApp() {
    var selectedItem by remember { mutableStateOf("Inbox") }

    Scaffold(
        sidebar = {
            Sidebar(
                items = listOf(
                    SidebarItem("Home", onClick = { selectedItem = "Home" }, icon = LucideHome),
                    SidebarItem("Favorites", onClick = { selectedItem = "Favorites" }, icon = LucideStar),
                    SidebarItem("Settings", onClick = { selectedItem = "Settings" }, icon = LucideSettings),
                ),
                activeItem = selectedItem,
            )
        },
        titleBar = {
            TitleBar(
                title = { Text("Mail") },
                actions = {
                    IconButton(
                        icon = Icons.Share2,
                        onClick = { /* ... */ },
                        style = IconButtonStyle.Borderless,
                    )
                },
            )
        },
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("Welcome to macOS UI", style = MacosTheme.typography.title1)
            PushButton(onClick = { /* ... */ }) {
                Text("Get Started")
            }
        }
    }
}
```

## Icons

Icons go through `SystemIcon`, which pairs a native SF Symbol name with a Lucide fallback:

```kotlin
data class SystemIcon(val sfSymbolName: String, val fallback: ImageVector)

// dev.nucleusframework.macoscompose.icons.Icons
val Home = SystemIcon("house", LucideHome)
val Settings = SystemIcon("gearshape", LucideSettings)
```

On macOS the SF Symbol is loaded natively through AppKit; every other platform draws the Lucide
vector. Prefer `Icons.*` over raw Lucide vectors so Apple platforms get the native glyph:

```kotlin
IconButton(icon = Icons.Share2, onClick = { /* ... */ })   // SF Symbol on macOS, Lucide elsewhere
IconButton(icon = LucideShare2, onClick = { /* ... */ })   // Lucide everywhere
```

`SidebarItem.icon` currently takes an `ImageVector`, so sidebar items always render the Lucide
vector even on macOS. Use `Icons.*` everywhere else.

`compose-macos-ui-icons-extended` adds the full icon set for icons not covered by `Icons`.

## Desktop: the decorated window

On desktop, macOS chrome is not something Compose can draw on its own: the traffic lights, the
window corner radius and the sidebar glass material are native surfaces. `MacosDecoratedWindow`
wraps [Nucleus](https://github.com/NucleusFramework)' `DecoratedWindow` (Tao backend — no AWT, no
bundled JNI) and reproduces SwiftUI's full-size content window:

- The window is `fullSizeContentView` with a transparent title bar, so Compose content fills the
  entire frame.
- A hidden `NSToolbar` is installed to get the macOS 26 large corner radius.
- Traffic lights are recentered natively against `titleBarHeight`. On Windows and Linux the window
  is fully undecorated and the platform window controls are drawn in that same band.
- Native surfaces (materials, traffic lights, window background) follow `MacosTheme` rather than the
  OS appearance — a dark app on a light system still gets a dark sidebar material.
- `Scaffold`'s sidebar extends to the top of the window with the traffic lights floating over its
  header, like `NavigationSplitView`.

### Gradle setup

The desktop entry point runs inside `nucleusApplication`, which needs the Nucleus Gradle plugin.
It also patches the JVM for the macOS 26.0 SDK (Liquid Glass) when you `run`.

```kotlin
// build.gradle.kts
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("dev.nucleusframework") version "<nucleus-version>"
}

nucleus.application {
    mainClass = "com.example.app.MainKt"

    nativeDistributions {
        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
        packageName = "com.example.app"
        packageVersion = "1.0.0"
    }
}
```

`compose-macos-ui` already exposes `nucleus.decorated-window-tao` and `nucleus.nucleus-application`
as `api` dependencies on the JVM target, so no extra runtime dependency is required.

### Entry point

```kotlin
import dev.nucleusframework.application.nucleusApplication
import dev.nucleusframework.macoscompose.window.MacosDecoratedWindow

fun main() = nucleusApplication {
    MacosDecoratedWindow(
        onCloseRequest = ::exitApplication,
        title = "My App",
    ) {
        App() // MacosTheme lives inside the window content
    }
}
```

`MacosTheme` must be inside the window content: the window sits above the theme and receives the
resolved appearance from it, which is what keeps the native surfaces in sync.

### Parameters

| Parameter | Default | Description |
|-----------|---------|-------------|
| `onCloseRequest` | — | Invoked when the user closes the window |
| `state` | `rememberWindowState()` | Position, size and maximized/minimized state |
| `visible` | `true` | Window visibility |
| `title` | `""` | Window title (shown in the Dock / taskbar) |
| `icon` | `null` | Window icon `Painter` |
| `resizable` | `true` | When `false`, the maximize button is dropped |
| `enabled` | `true` | Whether the window accepts input |
| `focusable` | `true` | Whether the window can take focus |
| `alwaysOnTop` | `false` | Keep the window above others |
| `hiddenFromDock` | `false` | Hide from the Dock/taskbar while staying visible and focusable (Linux: X11/XWayland only) |
| `minimumSize` | `null` | Minimum `DpSize` |
| `onPreviewKeyEvent` / `onKeyEvent` | `{ false }` | Window-level key handling |
| `titleBarHeight` | `52.dp` | Height of the native title bar band; match it to your `TitleBarStyle` |

The `content` lambda is a `NucleusDecoratedWindowScope`, so it can reach the window state and the
Nucleus chrome APIs (`WindowBackdrop`, `WindowAppearance`, …).

### Title bar height

`titleBarHeight` drives the native layer (traffic-light centering, Windows caption zone) while the
visible toolbar is drawn by `Scaffold`/`TitleBar`. Keep the two in agreement:

```kotlin
MacosDecoratedWindow(
    onCloseRequest = ::exitApplication,
    titleBarHeight = TitleBarStyle.Unified.height.dp,
) { /* ... */ }
```

### Platform notes

| | macOS | Windows | Linux |
|---|---|---|---|
| Traffic lights | Native AppKit, recentered | Compose-drawn `WindowControls` (trailing) | Compose-drawn `WindowControls` (trailing) |
| Corner radius | macOS 26 large radius (hidden `NSToolbar`) | Platform default | Platform default |
| Sidebar material | Native window glass region | Compose-drawn fallback | Compose-drawn fallback |

For the full component catalog, API details, and interactive demos, visit the [documentation](https://kdroidfilter.github.io/compose-macos-26-ui/).

## Gallery app

The sample doubles as a gallery. Native installers are built with GraalVM and attached to every
`v*` tag by the [Release Gallery](.github/workflows/release-gallery.yaml) workflow — grab them from
the [releases page](https://github.com/NucleusFramework/compose-macos-26-ui/releases).

To build one locally (the Nucleus plugin downloads the GraalVM 25 JDK itself):

```bash
./gradlew :sample:packageGraalvmDmg    # macOS
./gradlew :sample:packageGraalvmNsis   # Windows
./gradlew :sample:packageGraalvmDeb    # Linux
```

## Contributing

```bash
./gradlew :sample:run                            # desktop sample
./gradlew :sample:jsBrowserDevelopmentRun        # web (JS)
./gradlew :sample:wasmJsBrowserDevelopmentRun    # web (Wasm)
./gradlew detekt                                 # static analysis, required before committing
```

New components need a matching page in `sample/.../pages/` annotated with `@GalleryEntry`.

## License

Compose Macos UI is available under the [MIT License](LICENSE).
