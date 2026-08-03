package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.components.PushButton
import dev.nucleusframework.macoscompose.components.Tooltip
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources

@GalleryExample("Tooltip", "Default")
@Composable
fun TooltipDefaultExample() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Tooltip(text = "This is a tooltip!") { PushButton(text = "Hover me", onClick = {}) }
        Tooltip(text = "Another tooltip with more info") {
            PushButton(text = "More info", onClick = {})
        }
    }
}

@Composable
internal fun TooltipPage() {
    GalleryPage("Tooltip", "A popup that displays information related to an element on hover.") {
        SectionHeader("Examples")
        ExampleCard(title = "Default", sourceCode = GallerySources.TooltipDefaultExample) { TooltipDefaultExample() }
    }
}
