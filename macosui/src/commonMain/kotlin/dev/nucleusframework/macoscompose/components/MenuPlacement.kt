package dev.nucleusframework.macoscompose.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.window.PopupPositionProvider

/**
 * Controls where a dropdown menu appears relative to its anchor.
 */
enum class MenuPlacement {
    /** Menu appears above the anchor. */
    Above,

    /** Menu is centered over the anchor (overlapping). */
    Center,

    /** Menu appears below the anchor with a small gap (default). */
    Below,

    /** Menu starts at the anchor's top edge and extends below (overlapping). */
    OverlapBelow,
}

/**
 * Returns the [TransformOrigin] for scale animations matching the placement.
 */
internal fun MenuPlacement.transformOrigin(centerX: Boolean = false): TransformOrigin {
    val pivotX = if (centerX) 0.5f else 0f
    return when (this) {
        MenuPlacement.Above -> TransformOrigin(pivotX, 1f)
        MenuPlacement.Center -> TransformOrigin(0.5f, 0.5f)
        MenuPlacement.Below -> TransformOrigin(pivotX, 0f)
        MenuPlacement.OverlapBelow -> TransformOrigin(pivotX, 0f)
    }
}

/**
 * Calculates the menu Y position in absolute window coordinates.
 */
internal fun calculateMenuY(
    placement: MenuPlacement,
    anchorY: Int,
    anchorHeight: Int,
    menuHeight: Int,
    gapPx: Int,
): Int = when (placement) {
    MenuPlacement.Below -> anchorY + anchorHeight + gapPx
    MenuPlacement.Above -> anchorY - menuHeight - gapPx
    MenuPlacement.Center -> anchorY + (anchorHeight - menuHeight) / 2
    MenuPlacement.OverlapBelow -> anchorY
}

/**
 * Window-sized container for menus shown inside a full-window [androidx.compose.ui.window.Popup].
 *
 * [calculateOffset] runs during the placement pass, so both the window and the
 * menu are already measured the first time the menu is drawn. Deriving the
 * offset from `onSizeChanged` state instead leaves both sizes at zero for the
 * first frame, which renders the menu at the window's top-left corner before it
 * snaps into place.
 *
 * @param calculateOffset Menu position in window coordinates, given the window
 *   size and the measured menu size.
 */
@Composable
internal fun MenuPopupLayout(
    calculateOffset: (windowSize: IntSize, menuSize: IntSize) -> IntOffset,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        val windowSize = IntSize(constraints.maxWidth, constraints.maxHeight)
        val menuConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val placeables = measurables.fastMap { it.measure(menuConstraints) }
        layout(windowSize.width, windowSize.height) {
            placeables.fastForEach { placeable ->
                placeable.place(calculateOffset(windowSize, IntSize(placeable.width, placeable.height)))
            }
        }
    }
}

/**
 * [PopupPositionProvider] for dropdown menus using [MenuPlacement].
 */
internal class DropdownMenuPositionProvider(
    private val placement: MenuPlacement,
    private val gapPx: Int,
    private val horizontalOffsetPx: Int = 0,
) : PopupPositionProvider {

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {
        val x = anchorBounds.left + horizontalOffsetPx
        val y = calculateMenuY(
            placement = placement,
            anchorY = anchorBounds.top,
            anchorHeight = anchorBounds.height,
            menuHeight = popupContentSize.height,
            gapPx = gapPx,
        )
        return IntOffset(
            x = x.coerceIn(0, (windowSize.width - popupContentSize.width).coerceAtLeast(0)),
            y = y.coerceIn(0, (windowSize.height - popupContentSize.height).coerceAtLeast(0)),
        )
    }
}
