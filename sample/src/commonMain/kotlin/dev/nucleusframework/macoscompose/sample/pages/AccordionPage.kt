package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.nucleusframework.macoscompose.components.Accordion
import dev.nucleusframework.macoscompose.components.AccordionItem
import dev.nucleusframework.macoscompose.components.AccordionType
import dev.nucleusframework.macoscompose.components.Text
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources
import dev.nucleusframework.macoscompose.theme.MacosTheme

@GalleryExample("Accordion", "Single Mode")
@Composable
fun AccordionSingleModeExample() {
    var expandedItem by remember { mutableStateOf<String?>("item1") }
    Accordion(type = AccordionType.Single) {
        AccordionItem(
            value = "item1",
            expanded = expandedItem == "item1",
            onToggle = { expandedItem = if (expandedItem == "item1") null else "item1" },
            trigger = { Text("What is macOS UI?") },
            content = {
                Text(
                    "macOS UI is a macOS-inspired design system for Compose Multiplatform.",
                    color = MacosTheme.colorScheme.textSecondary,
                )
            },
        )
        AccordionItem(
            value = "item2",
            expanded = expandedItem == "item2",
            onToggle = { expandedItem = if (expandedItem == "item2") null else "item2" },
            trigger = { Text("Which platforms are supported?") },
            content = {
                Text(
                    "Android, iOS, Desktop (JVM), Web (JS), and WASM are all supported through Compose Multiplatform.",
                    color = MacosTheme.colorScheme.textSecondary,
                )
            },
        )
        AccordionItem(
            value = "item3",
            expanded = expandedItem == "item3",
            onToggle = { expandedItem = if (expandedItem == "item3") null else "item3" },
            trigger = { Text("Is dark mode supported?") },
            content = {
                Text(
                    "Yes! Dark mode is the default theme.",
                    color = MacosTheme.colorScheme.textSecondary,
                )
            },
        )
    }
}

@Composable
internal fun AccordionPage() {
    GalleryPage("Accordion", "A vertically stacked set of interactive headings that reveal content.") {
        SectionHeader("Examples")
        ExampleCard(title = "Single Mode", sourceCode = GallerySources.AccordionSingleModeExample) { AccordionSingleModeExample() }
    }
}
