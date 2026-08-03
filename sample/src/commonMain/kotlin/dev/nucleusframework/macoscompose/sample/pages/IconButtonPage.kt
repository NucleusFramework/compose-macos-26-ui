package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.components.IconButton
import dev.nucleusframework.macoscompose.components.IconButtonRole
import dev.nucleusframework.macoscompose.components.IconButtonStyle
import dev.nucleusframework.macoscompose.components.Text
import dev.nucleusframework.macoscompose.icons.Icons
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.sample.gallery.CodeBlock
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.PreviewContainer
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources
import dev.nucleusframework.macoscompose.theme.ControlSize
import dev.nucleusframework.macoscompose.theme.MacosTheme

@Composable
private fun IconButtonPreview() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(icon = Icons.Heart, onClick = {}, style = IconButtonStyle.Bordered)
        IconButton(icon = Icons.Star, onClick = {}, style = IconButtonStyle.BorderedProminent)
        IconButton(icon = Icons.Share2, onClick = {}, style = IconButtonStyle.Borderless)
        IconButton(icon = Icons.Trash2, onClick = {}, style = IconButtonStyle.Bordered, role = IconButtonRole.Destructive)
        IconButton(icon = Icons.Trash2, onClick = {}, style = IconButtonStyle.BorderedProminent, role = IconButtonRole.Destructive)
        IconButton(icon = Icons.Trash2, onClick = {}, style = IconButtonStyle.Borderless, role = IconButtonRole.Destructive)
    }
}

@GalleryExample("IconButton", "Styles")
@Composable
fun IconButtonStylesExample() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(icon = Icons.Heart, onClick = {}, style = IconButtonStyle.Bordered)
            IconButton(icon = Icons.Heart, onClick = {}, style = IconButtonStyle.Bordered, enabled = false)
            Text("Bordered", style = MacosTheme.typography.caption1, color = MacosTheme.colorScheme.textSecondary)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(icon = Icons.Star, onClick = {}, style = IconButtonStyle.BorderedProminent)
            IconButton(icon = Icons.Star, onClick = {}, style = IconButtonStyle.BorderedProminent, enabled = false)
            Text("Bordered Prominent", style = MacosTheme.typography.caption1, color = MacosTheme.colorScheme.textSecondary)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(icon = Icons.Share2, onClick = {}, style = IconButtonStyle.Borderless)
            IconButton(icon = Icons.Share2, onClick = {}, style = IconButtonStyle.Borderless, enabled = false)
            Text("Borderless", style = MacosTheme.typography.caption1, color = MacosTheme.colorScheme.textSecondary)
        }
    }
}

@GalleryExample("IconButton", "Destructive")
@Composable
fun IconButtonDestructiveExample() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(icon = Icons.Trash2, onClick = {}, style = IconButtonStyle.Bordered, role = IconButtonRole.Destructive)
            IconButton(icon = Icons.Trash2, onClick = {}, style = IconButtonStyle.BorderedProminent, role = IconButtonRole.Destructive)
            IconButton(icon = Icons.Trash2, onClick = {}, style = IconButtonStyle.Borderless, role = IconButtonRole.Destructive)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(icon = Icons.Trash2, onClick = {}, style = IconButtonStyle.Bordered, role = IconButtonRole.Destructive, enabled = false)
            IconButton(icon = Icons.Trash2, onClick = {}, style = IconButtonStyle.BorderedProminent, role = IconButtonRole.Destructive, enabled = false)
            IconButton(icon = Icons.Trash2, onClick = {}, style = IconButtonStyle.Borderless, role = IconButtonRole.Destructive, enabled = false)
        }
    }
}

@GalleryExample("IconButton", "Sizes")
@Composable
fun IconButtonSizesExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (size in ControlSize.entries) {
            ControlSize(size) {
                IconButton(icon = Icons.Heart, onClick = {}, style = IconButtonStyle.Bordered)
            }
        }
    }
}

@Composable
internal fun IconButtonPage() {
    GalleryPage("Icon Button", "Circular icon-only buttons for toolbar and content area actions.") {
        PreviewContainer { IconButtonPreview() }

        SectionHeader("Usage")
        CodeBlock("""IconButton(icon = Icons.Heart, onClick = {})
IconButton(icon = Icons.Star, onClick = {}, style = IconButtonStyle.BorderedProminent)
IconButton(icon = Icons.Trash2, onClick = {}, role = IconButtonRole.Destructive)""")

        SectionHeader("Styles")
        ExampleCard(
            title = "All Styles",
            description = "Bordered, Bordered Prominent, and Borderless with idle and disabled states",
            sourceCode = GallerySources.IconButtonStylesExample,
        ) { IconButtonStylesExample() }

        SectionHeader("Examples")
        ExampleCard(
            title = "Destructive",
            description = "All styles with destructive role",
            sourceCode = GallerySources.IconButtonDestructiveExample,
        ) { IconButtonDestructiveExample() }
        ExampleCard(
            title = "Sizes",
            description = "Icon button at each ControlSize level",
            sourceCode = GallerySources.IconButtonSizesExample,
        ) { IconButtonSizesExample() }
    }
}
