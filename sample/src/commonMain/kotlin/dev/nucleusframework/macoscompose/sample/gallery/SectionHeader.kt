package dev.nucleusframework.macoscompose.sample.gallery

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import dev.nucleusframework.macoscompose.components.Text
import dev.nucleusframework.macoscompose.theme.MacosTheme

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MacosTheme.typography.headline,
        fontWeight = FontWeight.SemiBold,
        color = MacosTheme.colorScheme.textPrimary,
    )
}
