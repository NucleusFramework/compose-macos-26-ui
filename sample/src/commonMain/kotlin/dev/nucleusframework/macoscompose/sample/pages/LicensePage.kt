package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mikepenz.markdown.model.rememberMarkdownState
import dev.nucleusframework.macoscompose.components.GroupBox
import composemacosui.sample.generated.resources.Res
import dev.nucleusframework.macoscompose.markdown.Markdown
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun LicensePage() {
    val markdownState = rememberMarkdownState {
        Res.readBytes("files/license.md").decodeToString()
    }

    GalleryPage("License") {
        GroupBox(modifier = Modifier.fillMaxWidth()) {
            Markdown(
                markdownState = markdownState,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
