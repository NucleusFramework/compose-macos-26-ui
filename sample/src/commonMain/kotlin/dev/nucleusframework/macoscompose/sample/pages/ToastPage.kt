package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.components.PushButton
import dev.nucleusframework.macoscompose.components.ToastState
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources

@OptIn(ExperimentalLayoutApi::class)
@GalleryExample("Toast", "Click to Show")
@Composable
fun ToastClickToShowExample(toastState: ToastState) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        PushButton(
            text = "Notification",
            onClick = {
                toastState.show(
                    title = "Calendar",
                    message = "You have a meeting in 15 minutes",
                    timestamp = "now",
                )
            },
        )
        PushButton(
            text = "Message",
            onClick = {
                toastState.show(
                    title = "Messages",
                    message = "John: Hey, are you free for lunch today?",
                    timestamp = "2m ago",
                    showCloseButton = true,
                )
            },
        )
        PushButton(
            text = "Download (10s)",
            onClick = {
                toastState.show(
                    title = "Downloads",
                    message = "Your file has been downloaded successfully.",
                    showCloseButton = true,
                    duration = 10_000L,
                    onDismiss = { println("Download toast dismissed") },
                )
            },
        )
        PushButton(
            text = "Clickable",
            onClick = {
                toastState.show(
                    title = "Update Available",
                    message = "Click to install the latest version.",
                    showCloseButton = true,
                    duration = 5000L,
                    onClick = { println("Toast clicked — opening update…") },
                    onDismiss = { println("Update toast dismissed") },
                )
            },
        )
        PushButton(
            text = "Persistent",
            onClick = {
                toastState.show(
                    title = "Connection lost",
                    message = "Check your internet connection and try again.",
                    showCloseButton = true,
                    duration = null,
                )
            },
        )
    }
}

@Composable
internal fun ToastPage(toastState: ToastState) {
    GalleryPage("Toast", "macOS-style notification banners that auto-dismiss.") {
        SectionHeader("Examples")
        ExampleCard(
            title = "Click to Show",
            description = "Trigger different notification banners",
            sourceCode = GallerySources.ToastClickToShowExample,
        ) { ToastClickToShowExample(toastState) }
    }
}
