package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.components.Popover
import dev.nucleusframework.macoscompose.components.PopoverPlacement
import dev.nucleusframework.macoscompose.components.PushButton
import dev.nucleusframework.macoscompose.components.Text
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources
import dev.nucleusframework.macoscompose.theme.MacosTheme

@GalleryExample("Popover", "Default")
@Composable
fun PopoverDefaultExample() {
    var popoverExpanded by remember { mutableStateOf(false) }
    Popover(
        expanded = popoverExpanded,
        onDismissRequest = { popoverExpanded = false },
        trigger = {
            PushButton(
                text = "Toggle Popover",
                onClick = { popoverExpanded = !popoverExpanded },
            )
        },
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Popover Content", fontWeight = FontWeight.SemiBold, color = MacosTheme.colorScheme.textPrimary)
            Text("This is a popover panel with rich content.", color = MacosTheme.colorScheme.textSecondary)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PushButton(
                    text = "Edit",
                    onClick = { popoverExpanded = false },
                )
                PushButton(
                    text = "Copy",
                    onClick = { popoverExpanded = false },
                )
            }
        }
    }
}

@GalleryExample("Popover", "Placement")
@Composable
fun PopoverPlacementExample() {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        PopoverPlacement.entries.filter { it != PopoverPlacement.Auto }.forEach { placement ->
            var expanded by remember { mutableStateOf(false) }
            Popover(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                placement = placement,
                trigger = {
                    PushButton(
                        text = placement.name,
                        onClick = { expanded = !expanded },
                    )
                },
            ) {
                Text("Placed ${placement.name}", color = MacosTheme.colorScheme.textPrimary)
            }
        }
    }
}

@Composable
internal fun PopoverPage() {
    GalleryPage("Popover", "Displays rich content in a portal, triggered by a button.") {
        SectionHeader("Examples")
        ExampleCard(title = "Default (Auto)", sourceCode = GallerySources.PopoverDefaultExample) { PopoverDefaultExample() }
        ExampleCard(title = "Placement", sourceCode = GallerySources.PopoverPlacementExample) { PopoverPlacementExample() }
    }
}
