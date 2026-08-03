package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.components.AlertBanner
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.PreviewContainer
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources

@Composable
private fun AlertPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        AlertBanner(
            title = "Calendar",
            message = "You have a meeting in 15 minutes",
            timestamp = "now",
        )
        AlertBanner(
            title = "Messages",
            message = "John: Hey, are you free for lunch today?",
            timestamp = "2m ago",
        )
    }
}

@GalleryExample("Alert", "Basic")
@Composable
fun AlertBasicExample() {
    AlertBanner(
        title = "Calendar",
        message = "You have a meeting in 15 minutes",
        timestamp = "now",
    )
}

@GalleryExample("Alert", "With Timestamp")
@Composable
fun AlertWithTimestampExample() {
    AlertBanner(
        title = "Messages",
        message = "John: Hey, are you free for lunch today?",
        timestamp = "2m ago",
    )
}

@GalleryExample("Alert", "Minimal")
@Composable
fun AlertMinimalExample() {
    AlertBanner(
        title = "Download Complete",
        message = "Your file has been downloaded successfully.",
    )
}

@Composable
internal fun AlertPage() {
    GalleryPage("Alert", "macOS-style notification banners.") {
        PreviewContainer { AlertPreview() }

        SectionHeader("Variants")
        ExampleCard(title = "Basic", sourceCode = GallerySources.AlertBasicExample) { AlertBasicExample() }
        ExampleCard(title = "With Timestamp", sourceCode = GallerySources.AlertWithTimestampExample) { AlertWithTimestampExample() }
        ExampleCard(title = "Minimal", sourceCode = GallerySources.AlertMinimalExample) { AlertMinimalExample() }
    }
}
