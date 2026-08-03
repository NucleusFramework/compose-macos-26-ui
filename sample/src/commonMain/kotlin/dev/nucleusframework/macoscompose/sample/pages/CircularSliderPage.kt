package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.components.CircularSlider
import dev.nucleusframework.macoscompose.components.Text
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources
import dev.nucleusframework.macoscompose.theme.ControlSize
import dev.nucleusframework.macoscompose.theme.MacosTheme

@GalleryExample("Circular Slider", "Sizes")
@Composable
fun CircularSliderSizesExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (size in ControlSize.entries) {
            ControlSize(size) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    var value by remember { mutableStateOf(0.5f) }
                    CircularSlider(value = value, onValueChange = { value = it })
                    Text(
                        size.name,
                        style = MacosTheme.typography.caption2,
                        color = MacosTheme.colorScheme.textTertiary,
                    )
                }
            }
        }
    }
}

@GalleryExample("Circular Slider", "Disabled")
@Composable
fun CircularSliderDisabledExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ControlSize(ControlSize.Regular) {
            CircularSlider(value = 0.4f, onValueChange = {}, enabled = false)
        }
        ControlSize(ControlSize.Large) {
            CircularSlider(value = 0.75f, onValueChange = {}, enabled = false)
        }
    }
}

@Composable
internal fun CircularSliderPage() {
    GalleryPage("Circular Slider", "A circular dial slider for value selection.") {
        SectionHeader("Sizes")
        ExampleCard(
            title = "All Sizes",
            description = "Circular slider at each ControlSize level",
            sourceCode = GallerySources.CircularSliderSizesExample,
        ) { CircularSliderSizesExample() }
        ExampleCard(
            title = "Disabled",
            description = "Circular sliders in disabled state",
            sourceCode = GallerySources.CircularSliderDisabledExample,
        ) { CircularSliderDisabledExample() }
    }
}
