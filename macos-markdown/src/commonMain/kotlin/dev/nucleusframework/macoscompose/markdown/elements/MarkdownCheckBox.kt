package dev.nucleusframework.macoscompose.markdown.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.mikepenz.markdown.compose.elements.MarkdownCheckBox
import dev.nucleusframework.macoscompose.components.CheckBox
import org.intellij.markdown.ast.ASTNode

@Composable
fun MarkdownCheckBox(
    content: String,
    node: ASTNode,
    style: TextStyle,
) = MarkdownCheckBox(
    content = content,
    node = node,
    style = style,
    checkedIndicator = { checked, modifier ->
        CheckBox(
            checked = checked,
            onCheckedChange = {},
            modifier = modifier,
        )
    },
)
