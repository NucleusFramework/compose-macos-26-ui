package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.FileText
import com.composables.icons.lucide.Lucide
import dev.nucleusframework.macoscompose.components.AddressBar
import dev.nucleusframework.macoscompose.components.DropdownMenuItem
import dev.nucleusframework.macoscompose.components.LocalTitleBarStyle
import dev.nucleusframework.macoscompose.components.NavigationButtons
import dev.nucleusframework.macoscompose.components.SearchSuggestionHeader
import dev.nucleusframework.macoscompose.components.SearchSuggestionItem
import dev.nucleusframework.macoscompose.components.SearchSuggestionSeparator
import dev.nucleusframework.macoscompose.components.SidebarButton
import dev.nucleusframework.macoscompose.components.Text
import dev.nucleusframework.macoscompose.components.TitleBar
import dev.nucleusframework.macoscompose.components.TitleBarButtonGroup
import dev.nucleusframework.macoscompose.components.TitleBarGroupButton
import dev.nucleusframework.macoscompose.components.TitleBarStyle
import dev.nucleusframework.macoscompose.components.ToolbarSearchField
import dev.nucleusframework.macoscompose.components.ToolbarSeparator
import dev.nucleusframework.macoscompose.components.WindowTitle
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.icons.Icon
import dev.nucleusframework.macoscompose.icons.LucideChevronDown
import dev.nucleusframework.macoscompose.icons.LucideCopy
import dev.nucleusframework.macoscompose.icons.LucideDownload
import dev.nucleusframework.macoscompose.icons.LucideEllipsis
import dev.nucleusframework.macoscompose.icons.LucideLayoutGrid
import dev.nucleusframework.macoscompose.icons.LucideList
import dev.nucleusframework.macoscompose.icons.LucidePlus
import dev.nucleusframework.macoscompose.icons.LucideSearch
import dev.nucleusframework.macoscompose.icons.LucideSettings
import dev.nucleusframework.macoscompose.icons.LucideShare2
import dev.nucleusframework.macoscompose.icons.LucideTag
import dev.nucleusframework.macoscompose.icons.LucideUpload
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources

// =====================================================================
// Window Title variants (Sketch: Toolbar/_/Window Titles/Standard)
// =====================================================================

@GalleryExample("TitleBar", "Window Title Variants")
@Composable
fun TitleBarWindowTitleExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WindowTitle(title = "Window Title")
        WindowTitle(
            title = "Window Title",
            proxyIcon = { Icon(Lucide.FileText, modifier = Modifier.size(15.dp)) },
        )
        WindowTitle(
            title = "Window Title",
            subtitle = "Subtitle — Edited",
        )
    }
}

// =====================================================================
// Toolbar Buttons (Sketch: Toolbar/XL/Buttons and Toolbar/Medium/Buttons)
// =====================================================================

@GalleryExample("TitleBar", "Toolbar Buttons XL")
@Composable
fun TitleBarButtonsXLExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TitleBarButtonGroup {
            TitleBarGroupButton(onClick = {}) {
                Icon(LucideSettings, modifier = Modifier.size(14.dp))
            }
        }
        TitleBarButtonGroup {
            TitleBarGroupButton(onClick = {}) {
                Icon(LucideDownload, modifier = Modifier.size(14.dp))
            }
            TitleBarGroupButton(onClick = {}) {
                Icon(LucideUpload, modifier = Modifier.size(14.dp))
            }
        }
        TitleBarButtonGroup {
            TitleBarGroupButton(onClick = {}) {
                Icon(LucideDownload, modifier = Modifier.size(14.dp))
            }
            TitleBarGroupButton(onClick = {}) {
                Icon(LucideUpload, modifier = Modifier.size(14.dp))
            }
            TitleBarGroupButton(onClick = {}) {
                Icon(LucideCopy, modifier = Modifier.size(14.dp))
            }
            TitleBarGroupButton(onClick = {}) {
                Icon(LucideShare2, modifier = Modifier.size(14.dp))
            }
        }
        TitleBarButtonGroup {
            TitleBarGroupButton(onClick = {}, enabled = false) {
                Icon(LucideSettings, modifier = Modifier.size(14.dp))
            }
        }
    }
}

@GalleryExample("TitleBar", "Toolbar Buttons Medium")
@Composable
fun TitleBarButtonsMediumExample() {
    CompositionLocalProvider(LocalTitleBarStyle provides TitleBarStyle.UnifiedCompact) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TitleBarButtonGroup {
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideSettings, modifier = Modifier.size(10.dp))
                }
            }
            TitleBarButtonGroup {
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideDownload, modifier = Modifier.size(10.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideUpload, modifier = Modifier.size(10.dp))
                }
            }
            TitleBarButtonGroup {
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideDownload, modifier = Modifier.size(10.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideUpload, modifier = Modifier.size(10.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideCopy, modifier = Modifier.size(10.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideShare2, modifier = Modifier.size(10.dp))
                }
            }
            TitleBarButtonGroup {
                TitleBarGroupButton(onClick = {}, enabled = false) {
                    Icon(LucideSettings, modifier = Modifier.size(10.dp))
                }
            }
        }
    }
}

// =====================================================================
// Toolbar Separator (Sketch: _Separator)
// =====================================================================

@GalleryExample("TitleBar", "Toolbar Separator")
@Composable
fun TitleBarSeparatorExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NavigationButtons(onBack = {}, onForward = {}, backEnabled = true, forwardEnabled = true)
        NavigationButtons(onBack = {}, onForward = {}, backEnabled = true, forwardEnabled = false)
        NavigationButtons(onBack = {}, onForward = {}, backEnabled = false, forwardEnabled = false)
    }
}

// =====================================================================
// Full TitleBar compositions
// =====================================================================

@GalleryExample("TitleBar", "Browser")
@Composable
fun TitleBarBrowserExample() {
    var urlText by remember { mutableStateOf("apple.com") }
    TitleBar(
        navigationActions = {
            SidebarButton(
                onClick = {},
                menuContent = {
                    DropdownMenuItem(onClick = {}) { Text("Bookmarks") }
                    DropdownMenuItem(onClick = {}) { Text("Reading List") }
                    DropdownMenuItem(onClick = {}) { Text("Shared with You") }
                },
            )
            Spacer(modifier = Modifier.width(8.dp))
            NavigationButtons(
                onBack = {},
                onForward = {},
                backEnabled = true,
                forwardEnabled = false,
            )
        },
        title = {
            AddressBar(
                value = urlText,
                onValueChange = { urlText = it },
                onGo = {},
                leadingIcon = { Icon(LucideSearch, modifier = Modifier.size(13.dp)) },
                modifier = Modifier.fillMaxWidth(),
            )
        },
        actions = {
            TitleBarButtonGroup {
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideDownload, modifier = Modifier.size(14.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideUpload, modifier = Modifier.size(14.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucidePlus, modifier = Modifier.size(14.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideCopy, modifier = Modifier.size(14.dp))
                }
            }
        },
    )
}

@GalleryExample("TitleBar", "Finder")
@Composable
fun TitleBarFinderExample() {
    var searchQuery by remember { mutableStateOf("") }
    var searchExpanded by remember { mutableStateOf(false) }

    TitleBar(
        navigationActions = {
            NavigationButtons(
                onBack = {},
                onForward = {},
                backEnabled = true,
                forwardEnabled = false,
            )
        },
        title = {
            Text("Documents")
        },
        actions = {
            TitleBarButtonGroup {
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideLayoutGrid, modifier = Modifier.size(14.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideList, modifier = Modifier.size(14.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideChevronDown, modifier = Modifier.size(10.dp))
                }
            }

            TitleBarButtonGroup {
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideShare2, modifier = Modifier.size(14.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideTag, modifier = Modifier.size(14.dp))
                }
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideEllipsis, modifier = Modifier.size(14.dp))
                }
            }

            ToolbarSeparator()

            ToolbarSearchField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                expanded = searchExpanded,
                onExpandedChange = { searchExpanded = it },
                expandedWidth = 220.dp,
                suggestions = {
                    SearchSuggestionHeader("File names")
                    SearchSuggestionItem(onClick = {}) {
                        Text("Name contains \"$searchQuery\"")
                    }
                    SearchSuggestionSeparator()
                    SearchSuggestionHeader("Content")
                    SearchSuggestionItem(onClick = {}) {
                        Text("Contains \"$searchQuery\"")
                    }
                    SearchSuggestionSeparator()
                    SearchSuggestionHeader("Types")
                    SearchSuggestionItem(onClick = {}) { Text("SVG Document") }
                    SearchSuggestionItem(onClick = {}) { Text("Source Code") }
                    SearchSuggestionItem(onClick = {}) { Text("PDF Document") }
                },
            )
        },
    )
}

@GalleryExample("TitleBar", "Document")
@Composable
fun TitleBarDocumentExample() {
    TitleBar(
        title = {
            WindowTitle(
                title = "Document.txt",
                subtitle = "Subtitle — Edited",
                proxyIcon = { Icon(Lucide.FileText, modifier = Modifier.size(15.dp)) },
            )
        },
        actions = {
            TitleBarButtonGroup {
                TitleBarGroupButton(onClick = {}) {
                    Icon(LucideShare2, modifier = Modifier.size(14.dp))
                }
            }
        },
    )
}

// =====================================================================
// Page
// =====================================================================

@Composable
internal fun TitleBarPage() {
    GalleryPage("TitleBar", "macOS-style title bar with window controls, navigation, and grouped toolbar actions.") {
        SectionHeader("Window Titles")
        ExampleCard(
            title = "Window Title Variants",
            description = "Title only, title + proxy icon, title + subtitle — matching Sketch specs",
            sourceCode = GallerySources.TitleBarWindowTitleExample,
        ) { TitleBarWindowTitleExample() }

        SectionHeader("Toolbar Buttons")
        ExampleCard(
            title = "XL Buttons (36px)",
            description = "Button groups with 1, 2, 4 icons and disabled state — standard toolbar size",
            sourceCode = GallerySources.TitleBarButtonsXLExample,
        ) { TitleBarButtonsXLExample() }
        ExampleCard(
            title = "Medium Buttons (24px)",
            description = "Button groups in compact toolbar size",
            sourceCode = GallerySources.TitleBarButtonsMediumExample,
        ) { TitleBarButtonsMediumExample() }

        SectionHeader("Separator")
        ExampleCard(
            title = "Toolbar Separator",
            description = "Vertical separator between button groups, like SwiftUI Divider() in .toolbar",
            sourceCode = GallerySources.TitleBarSeparatorExample,
        ) { TitleBarSeparatorExample() }

        SectionHeader("Full Title Bars")
        ExampleCard(
            title = "Browser",
            description = "Safari-style toolbar: sidebar, back/forward, URL field, and action group",
            sourceCode = GallerySources.TitleBarBrowserExample,
        ) { TitleBarBrowserExample() }
        ExampleCard(
            title = "Document",
            description = "Document window with proxy icon, subtitle, and share action",
            sourceCode = GallerySources.TitleBarDocumentExample,
        ) { TitleBarDocumentExample() }
        ExampleCard(
            title = "Finder",
            description = "Finder-style toolbar: navigation, title, view modes, actions, and search",
            sourceCode = GallerySources.TitleBarFinderExample,
        ) { TitleBarFinderExample() }
    }
}
