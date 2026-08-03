package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.components.AvatarData
import dev.nucleusframework.macoscompose.components.Avatar
import dev.nucleusframework.macoscompose.components.AvatarGroup
import dev.nucleusframework.macoscompose.gallery.GalleryExample
import dev.nucleusframework.macoscompose.sample.gallery.ExampleCard
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.sample.gallery.generated.GallerySources

@GalleryExample("Avatar", "Sizes")
@Composable
fun AvatarSizesExample() {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Avatar(name = "Alice Smith")
        Avatar(name = "Bob Jones", size = 48.dp)
        Avatar(name = "Carol White", size = 56.dp)
        Avatar(name = "Dan Brown")
    }
}

@GalleryExample("Avatar", "Avatar Group")
@Composable
fun AvatarGroupExample() {
    AvatarGroup(
        avatars =
            listOf(
                AvatarData(name = "Alice Smith"),
                AvatarData(name = "Bob Jones"),
                AvatarData(name = "Carol White"),
                AvatarData(name = "Dan Brown"),
                AvatarData(name = "Eve Taylor"),
                AvatarData(name = "Frank Lee"),
            ),
        maxDisplay = 4,
    )
}

@Composable
internal fun AvatarPage() {
    GalleryPage("Avatar", "An image element with a fallback for representing the user.") {
        SectionHeader("Examples")
        ExampleCard(title = "Sizes", sourceCode = GallerySources.AvatarSizesExample) { AvatarSizesExample() }
        ExampleCard(title = "Avatar Group", sourceCode = GallerySources.AvatarGroupExample) { AvatarGroupExample() }
    }
}
