package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.components.Card
import dev.nucleusframework.macoscompose.components.CardContent
import dev.nucleusframework.macoscompose.components.CardDescription
import dev.nucleusframework.macoscompose.components.CardFooter
import dev.nucleusframework.macoscompose.components.CardHeader
import dev.nucleusframework.macoscompose.components.CardTitle
import dev.nucleusframework.macoscompose.components.PushButton
import dev.nucleusframework.macoscompose.components.Text
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.sample.gallery.CodeBlock
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.PreviewContainer
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources
import dev.nucleusframework.macoscompose.theme.MacosTheme

@GalleryExample("Card", "Default")
@Composable
fun CardDefaultExample() {

    Card(modifier = Modifier.widthIn(max = 384.dp).fillMaxWidth()) {
        CardHeader {
            CardTitle { Text("Card Title") }
            CardDescription { Text("This is a description of the card content.") }
        }
        CardContent {
            Text(
                "Cards can contain any content including text, images, and other components.",
                color = MacosTheme.colorScheme.mutedForeground,
            )
        }
        CardFooter {
            PushButton(text = "Action", onClick = {})
            PushButton(text = "Cancel", onClick = {})
        }
    }
}

@Composable
internal fun CardPage() {
    GalleryPage("Card", "Displays a card with header, content, and footer.") {
        PreviewContainer { CardDefaultExample() }

        SectionHeader("Usage")
        CodeBlock("""Card {
    CardHeader {
        CardTitle { Text("Title") }
        CardDescription { Text("Description") }
    }
    CardContent { Text("Content") }
    CardFooter { PushButton(text = "Action", onClick = {}) }
}""")

        SectionHeader("Examples")
        ExampleCard(title = "Default", sourceCode = GallerySources.CardDefaultExample) { CardDefaultExample() }
    }
}
