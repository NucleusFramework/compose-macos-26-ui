# Compose Macos UI

A Compose Multiplatform adaptation of the native macos 26 style. This port brings the same design language to Android, iOS, Desktop, and Web through Compose Multiplatform.

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.0-7F52FF.svg?logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.10.0-4285F4.svg?logo=jetpackcompose)](https://www.jetbrains.com/compose-multiplatform/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[Documentation & Live Demo](https://kdroidfilter.github.io/compose-macos-26-ui/)

## Features

- **30+ components** — Buttons, Cards, Dialogs, Tables, Toasts, and more
- **Light & Dark mode** — Theme-aware styling with automatic system detection
- **macOS aesthetic** — Clean, native-feeling design inspired by Apple's design language
- **Compose Multiplatform** — Runs on Android, iOS, Desktop (JVM), and Web (JS/Wasm)
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
        // Access design tokens via MacosTheme.colors, MacosTheme.typography, etc.
    }
}
```

### 3. Use components

```kotlin
import dev.nucleusframework.macoscompose.components.*

@Composable
fun MyApp() {
    var selectedItem by remember { mutableStateOf("inbox") }

    Scaffold(
        sidebar = {
            Sidebar(
                items = listOf(
                    SidebarItem("inbox", "Inbox", icon = Icons.Home),
                    SidebarItem("sent", "Sent", icon = Icons.Share2),
                    SidebarItem("settings", "Settings", icon = Icons.Settings),
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
                        onClick = { /* open https://github.com/kdroidFilter/compose-macos-26-ui */ },
                        style = IconButtonStyle.Borderless,
                    )
                },
            )
        },
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("Welcome to macOS UI", style = MacosTheme.typography.title1)
            PrimaryButton(onClick = { /* ... */ }) {
                Text("Get Started")
            }
        }
    }
}
```

For the full component catalog, API details, and interactive demos, visit the [documentation](https://kdroidfilter.github.io/compose-macos-26-ui/).

## License

Compose Macos UI is available under the [MIT License](LICENSE).
