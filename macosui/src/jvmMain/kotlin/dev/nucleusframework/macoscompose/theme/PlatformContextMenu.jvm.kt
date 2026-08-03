@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package dev.nucleusframework.macoscompose.theme

import androidx.compose.foundation.text.contextmenu.data.TextContextMenuComponent
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuItemWithComposableLeadingIcon

internal actual fun TextContextMenuComponent.toItemInfo(): ContextMenuItemInfo? {
    if (this !is TextContextMenuItemWithComposableLeadingIcon) return null
    return ContextMenuItemInfo(
        key = key,
        label = label,
        enabled = enabled,
        onClick = onClick,
    )
}
