package dev.nucleusframework.macoscompose.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.clickable
import com.composables.icons.lucide.Monitor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.composables.icons.lucide.Bell
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.ChevronsUpDown
import com.composables.icons.lucide.CircleDot
import com.composables.icons.lucide.CircleUser
import com.composables.icons.lucide.Columns3
import com.composables.icons.lucide.CreditCard
import com.composables.icons.lucide.Ellipsis
import com.composables.icons.lucide.Github
import com.composables.icons.lucide.GripVertical
import com.composables.icons.lucide.Layers
import com.composables.icons.lucide.LayoutList
import com.composables.icons.lucide.ListChecks
import com.composables.icons.lucide.Loader
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Menu
import com.composables.icons.lucide.MessageCircle
import com.composables.icons.lucide.MessageSquare
import com.composables.icons.lucide.MousePointerClick
import com.composables.icons.lucide.PanelTopOpen
import com.composables.icons.lucide.Scan
import com.composables.icons.lucide.Search
import com.composables.icons.lucide.SlidersHorizontal
import com.composables.icons.lucide.SquareCheck
import com.composables.icons.lucide.SquareDashed
import com.composables.icons.lucide.Table
import com.composables.icons.lucide.Tag
import com.composables.icons.lucide.TextAlignStart
import com.composables.icons.lucide.TextCursorInput
import com.composables.icons.lucide.ToggleLeft
import com.composables.icons.lucide.TriangleAlert
import dev.nucleusframework.macoscompose.components.ColumnVisibility
import dev.nucleusframework.macoscompose.components.NavigationButtons
import dev.nucleusframework.macoscompose.components.Popover
import dev.nucleusframework.macoscompose.components.PopoverPlacement
import dev.nucleusframework.macoscompose.components.Scaffold
import dev.nucleusframework.macoscompose.components.SearchSuggestionHeader
import dev.nucleusframework.macoscompose.components.SearchSuggestionItem
import dev.nucleusframework.macoscompose.components.SearchSuggestionSeparator
import dev.nucleusframework.macoscompose.components.SegmentedControl
import dev.nucleusframework.macoscompose.components.Sidebar
import dev.nucleusframework.macoscompose.components.SidebarItem
import dev.nucleusframework.macoscompose.components.Switch
import dev.nucleusframework.macoscompose.components.Text
import dev.nucleusframework.macoscompose.components.TitleBar
import dev.nucleusframework.macoscompose.components.TitleBarButtonGroup
import dev.nucleusframework.macoscompose.components.TitleBarGroupButton
import dev.nucleusframework.macoscompose.components.ToastHost
import dev.nucleusframework.macoscompose.components.ToolbarSearchField
import dev.nucleusframework.macoscompose.components.TrackClickBehavior
import dev.nucleusframework.macoscompose.components.VerticalScrollbar
import dev.nucleusframework.macoscompose.components.rememberScrollbarState
import dev.nucleusframework.macoscompose.components.rememberToastState
import dev.nucleusframework.macoscompose.icons.Icon
import dev.nucleusframework.macoscompose.icons.Icons
import dev.nucleusframework.macoscompose.icons.LucideHome
import dev.nucleusframework.macoscompose.icons.LucideMoon
import dev.nucleusframework.macoscompose.icons.LucideSettings
import dev.nucleusframework.macoscompose.icons.LucideSun
import dev.nucleusframework.macoscompose.icons.RadixPanelLeft
import dev.nucleusframework.macoscompose.icons.extended.ExternalLink
import dev.nucleusframework.macoscompose.icons.extended.IconsExtended
import dev.nucleusframework.macoscompose.sample.pages.AccordionPage
import dev.nucleusframework.macoscompose.sample.pages.AddressBarPage
import dev.nucleusframework.macoscompose.sample.pages.AlertPage
import dev.nucleusframework.macoscompose.sample.pages.AvatarPage
import dev.nucleusframework.macoscompose.sample.pages.BadgePage
import dev.nucleusframework.macoscompose.sample.pages.ButtonPage
import dev.nucleusframework.macoscompose.sample.pages.CardPage
import dev.nucleusframework.macoscompose.sample.pages.CheckboxPage
import dev.nucleusframework.macoscompose.sample.pages.CircularSliderPage
import dev.nucleusframework.macoscompose.sample.pages.ColorWellPage
import dev.nucleusframework.macoscompose.sample.pages.ComboBoxPage
import dev.nucleusframework.macoscompose.sample.pages.ContextMenuPage
import dev.nucleusframework.macoscompose.sample.pages.ControlSizePage
import dev.nucleusframework.macoscompose.sample.pages.DatePickerPage
import dev.nucleusframework.macoscompose.sample.pages.DialogPage
import dev.nucleusframework.macoscompose.sample.pages.DropdownMenuPage
import dev.nucleusframework.macoscompose.sample.pages.FormPage
import dev.nucleusframework.macoscompose.sample.pages.GettingStartedPage
import dev.nucleusframework.macoscompose.sample.pages.GroupBoxPage
import dev.nucleusframework.macoscompose.sample.pages.GroupedListPage
import dev.nucleusframework.macoscompose.sample.pages.HomePage
import dev.nucleusframework.macoscompose.sample.pages.IconButtonPage
import dev.nucleusframework.macoscompose.sample.pages.IconsPage
import dev.nucleusframework.macoscompose.sample.pages.InputPage
import dev.nucleusframework.macoscompose.sample.pages.LicensePage
import dev.nucleusframework.macoscompose.sample.pages.MaterialPage
import dev.nucleusframework.macoscompose.sample.pages.MultiSelectPage
import dev.nucleusframework.macoscompose.sample.pages.PageControlPage
import dev.nucleusframework.macoscompose.sample.pages.PopoverPage
import dev.nucleusframework.macoscompose.sample.pages.PopupButtonPage
import dev.nucleusframework.macoscompose.sample.pages.ProgressPage
import dev.nucleusframework.macoscompose.sample.pages.RadioButtonPage
import dev.nucleusframework.macoscompose.sample.pages.ScaffoldPage
import dev.nucleusframework.macoscompose.sample.pages.ScrollbarPage
import dev.nucleusframework.macoscompose.sample.pages.SearchInputPage
import dev.nucleusframework.macoscompose.sample.pages.SegmentedControlPage
import dev.nucleusframework.macoscompose.sample.pages.SidebarPage
import dev.nucleusframework.macoscompose.sample.pages.SkeletonPage
import dev.nucleusframework.macoscompose.sample.pages.SurfacePage
import dev.nucleusframework.macoscompose.sample.pages.SliderPage
import dev.nucleusframework.macoscompose.sample.pages.StepperPage
import dev.nucleusframework.macoscompose.sample.pages.SwitchPage
import dev.nucleusframework.macoscompose.sample.pages.TablePage
import dev.nucleusframework.macoscompose.sample.pages.TabsPage
import dev.nucleusframework.macoscompose.sample.pages.TextAreaPage
import dev.nucleusframework.macoscompose.sample.pages.TitleBarPage
import dev.nucleusframework.macoscompose.sample.pages.ToastPage
import dev.nucleusframework.macoscompose.sample.pages.TooltipPage
import dev.nucleusframework.macoscompose.theme.AccentColor
import dev.nucleusframework.macoscompose.theme.ControlSize
import dev.nucleusframework.macoscompose.theme.GlassType
import dev.nucleusframework.macoscompose.theme.MacosTheme
import dev.nucleusframework.macoscompose.theme.VibrantColors
import dev.nucleusframework.macoscompose.theme.vibrant

internal enum class ThemeMode { System, Light, Dark }

// Navigation data
internal data class SidebarEntryDef(val id: String, val label: String, val group: String, val icon: ImageVector)

internal val sidebarEntryDefs = listOf(
    SidebarEntryDef("home", "Home", "GENERAL", LucideHome),
    SidebarEntryDef("getting-started", "Getting Started", "GENERAL", Lucide.LayoutList),
    SidebarEntryDef("license", "License", "GENERAL", Lucide.Tag),
    SidebarEntryDef("button", "Button", "FORM CONTROLS", Lucide.MousePointerClick),
    SidebarEntryDef("iconbutton", "Icon Button", "FORM CONTROLS", Lucide.CircleDot),
    SidebarEntryDef("input", "Input", "FORM CONTROLS", Lucide.TextCursorInput),
    SidebarEntryDef("textarea", "Textarea", "FORM CONTROLS", Lucide.TextAlignStart),
    SidebarEntryDef("checkbox", "Checkbox", "FORM CONTROLS", Lucide.SquareCheck),
    SidebarEntryDef("radiobutton", "Radio Button", "FORM CONTROLS", Lucide.CircleDot),
    SidebarEntryDef("switch", "Switch", "FORM CONTROLS", Lucide.ToggleLeft),
    SidebarEntryDef("combobox", "Combo Box", "FORM CONTROLS", Lucide.ChevronsUpDown),
    SidebarEntryDef("multiselect", "Multi Select", "FORM CONTROLS", Lucide.ListChecks),
    SidebarEntryDef("searchinput", "Search Input", "FORM CONTROLS", Lucide.Search),
    SidebarEntryDef("slider", "Slider", "FORM CONTROLS", Lucide.SlidersHorizontal),
    SidebarEntryDef("circularslider", "Circular Slider", "FORM CONTROLS", Lucide.Loader),
    SidebarEntryDef("stepper", "Stepper", "FORM CONTROLS", Lucide.ChevronsUpDown),
    SidebarEntryDef("popupbutton", "Pop-up Button", "FORM CONTROLS", Lucide.ChevronsUpDown),
    SidebarEntryDef("datepicker", "Date Picker", "FORM CONTROLS", Lucide.Calendar),
    SidebarEntryDef("colorwell", "Color Wells & Pickers", "FORM CONTROLS", Lucide.Scan),
    SidebarEntryDef("scrollbar", "Scrollbar", "DATA DISPLAY", Lucide.GripVertical),
    SidebarEntryDef("groupbox", "Group Box", "DATA DISPLAY", Lucide.SquareDashed),
    SidebarEntryDef("groupedlist", "Grouped List", "DATA DISPLAY", Lucide.ListChecks),
    SidebarEntryDef("form", "Form", "DATA DISPLAY", Lucide.LayoutList),
    SidebarEntryDef("badge", "Badge", "DATA DISPLAY", Lucide.Tag),
    SidebarEntryDef("avatar", "Avatar", "DATA DISPLAY", Lucide.CircleUser),
    SidebarEntryDef("surface", "Surface", "DATA DISPLAY", Lucide.Layers),
    SidebarEntryDef("card", "Card", "DATA DISPLAY", Lucide.CreditCard),
    SidebarEntryDef("table", "Table", "DATA DISPLAY", Lucide.Table),
    SidebarEntryDef("pagecontrol", "Page Control", "DATA DISPLAY", Lucide.CircleDot),
    SidebarEntryDef("progress", "Progress", "DATA DISPLAY", Lucide.Loader),
    SidebarEntryDef("skeleton", "Skeleton", "DATA DISPLAY", Lucide.Scan),
    SidebarEntryDef("alert", "Alert", "FEEDBACK", Lucide.TriangleAlert),
    SidebarEntryDef("toast", "Toast", "FEEDBACK", Lucide.Bell),
    SidebarEntryDef("dialog", "Dialog", "OVERLAYS", Lucide.MessageSquare),
    SidebarEntryDef("tooltip", "Tooltip", "OVERLAYS", Lucide.MessageCircle),
    SidebarEntryDef("popover", "Popover", "OVERLAYS", Lucide.PanelTopOpen),
    SidebarEntryDef("dropdown", "Dropdown Menu", "OVERLAYS", Lucide.Menu),
    SidebarEntryDef("contextmenu", "Context Menu", "OVERLAYS", Lucide.Ellipsis),
    SidebarEntryDef("tabs", "Tabs", "NAVIGATION", Lucide.Columns3),
    SidebarEntryDef("accordion", "Accordion", "NAVIGATION", Lucide.ChevronsUpDown),
    SidebarEntryDef("sidebar", "Sidebar", "NAVIGATION", RadixPanelLeft),
    SidebarEntryDef("segmentedcontrol", "Segmented Control", "FORM CONTROLS", Lucide.Columns3),
    SidebarEntryDef("titlebar", "Title Bar", "NAVIGATION", Lucide.PanelTopOpen),
    SidebarEntryDef("addressbar", "Address Bar", "NAVIGATION", Lucide.Search),
    SidebarEntryDef("scaffold", "Scaffold", "NAVIGATION", RadixPanelLeft),
    SidebarEntryDef("icons", "Icons", "THEME", Lucide.Scan),
    SidebarEntryDef("material", "Material", "THEME", Lucide.Scan),
    SidebarEntryDef("controlsize", "Control Size", "THEME", Lucide.SlidersHorizontal),
)

@Composable
fun App() {
    var themeMode by remember { mutableStateOf(ThemeMode.System) }
    val systemDark = isSystemDarkMode()
    val isDark = when (themeMode) {
        ThemeMode.System -> systemDark
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
    }
    val systemRawColor = systemAccentRawColor()
    var overriddenAccent by remember { mutableStateOf<AccentColor?>(null) }
    val accentColor = overriddenAccent ?: AccentColor.Blue
    var sidebarControlSize by remember { mutableStateOf(ControlSize.Regular) }
    var isVibrant by remember { mutableStateOf(false) }
    var glassType by remember { mutableStateOf(GlassType.Regular) }

    val baseColorScheme = if (isDark) {
        dev.nucleusframework.macoscompose.theme.darkColorScheme(accentColor)
    } else {
        dev.nucleusframework.macoscompose.theme.lightColorScheme(accentColor)
    }
    // Override accent with exact system color when no manual override is set
    val withSystemColor = if (systemRawColor != null && overriddenAccent == null) {
        baseColorScheme.copy(
            accent = systemRawColor,
            info = systemRawColor,
            ring = systemRawColor,
            inputFocusBorder = systemRawColor,
            surfaceTint = systemRawColor,
            tertiary = systemRawColor,
        )
    } else {
        baseColorScheme
    }
    val colorScheme = if (isVibrant) {
        val vibrant = if (isDark) VibrantColors.dark() else VibrantColors.light()
        withSystemColor.vibrant(vibrant, accentColor)
    } else {
        withSystemColor
    }

    MacosTheme(darkTheme = isDark, accentColor = accentColor, colorScheme = colorScheme, glassType = glassType) {
        val toastState = rememberToastState()

        // Navigation state — back stack + forward stack for browser-like history
        val backStack = remember { mutableStateListOf<AppNavKey>(HomeScreen) }
        val forwardStack = remember { mutableStateListOf<AppNavKey>() }
        val nav = remember { NavigationState(backStack, forwardStack) }

        // On web, ChronologicalBrowserNavigation syncs URL ↔ back stack
        // On other platforms, this is a no-op
        BrowserNavigation(backStack)

        var searchQuery by remember { mutableStateOf("") }
        var searchExpanded by remember { mutableStateOf(false) }
        var columnVisibility by remember { mutableStateOf(ColumnVisibility.All) }
        var sidebarCollapsed by remember { mutableStateOf(false) }
        var settingsExpanded by remember { mutableStateOf(false) }

        // Navigation helpers
        val currentPageLabel = sidebarEntryDefs.firstOrNull { it.id == nav.currentPageId }?.label ?: ""

        val sidebarItems = sidebarEntryDefs.map { def ->
            SidebarItem(
                label = def.label,
                onClick = { nav.navigateTo(def.id) },
                icon = def.icon,
                group = def.group,
                id = def.id,
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                columnVisibility = columnVisibility,
                onColumnVisibilityChange = { columnVisibility = it },
                sidebar = {
                    ControlSize(sidebarControlSize) {
                        Sidebar(
                            items = sidebarItems,
                            activeItem = nav.currentPageId,
                            showBorder = false,
                            collapsed = sidebarCollapsed,
                            onCollapsedChange = { sidebarCollapsed = it },
                            collapsible = true,
                        )
                    }
                },
                titleBar = {
                    TitleBar(
                        glass = true,
                        navigationActions = {
                            NavigationButtons(
                                onBack = { nav.goBack() },
                                onForward = { nav.goForward() },
                                backEnabled = nav.canGoBack,
                                forwardEnabled = nav.canGoForward,
                            )
                        },
                        title = { Text(currentPageLabel) },
                        actions = {
                            Popover(
                                expanded = settingsExpanded,
                                onDismissRequest = { settingsExpanded = false },
                                placement = PopoverPlacement.Bottom,
                                trigger = {
                                    TitleBarButtonGroup {
                                        TitleBarGroupButton(onClick = { settingsExpanded = !settingsExpanded }) {
                                            Icon(LucideSettings, modifier = Modifier.size(14.dp))
                                        }
                                    }
                                },
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp).width(220.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp),
                                ) {
                                    Text(
                                        text = "Accent Color",
                                        style = MacosTheme.typography.caption1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MacosTheme.colorScheme.textSecondary,
                                    )
                                    AccentColorPicker(
                                        selected = accentColor,
                                        onSelect = { overriddenAccent = it },
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = "Vibrant",
                                            style = MacosTheme.typography.caption1,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MacosTheme.colorScheme.textSecondary,
                                        )
                                        ControlSize(ControlSize.Mini) {
                                            Switch(
                                                checked = isVibrant,
                                                onCheckedChange = { isVibrant = it },
                                            )
                                        }
                                    }
                                    Text(
                                        text = "Glass Type",
                                        style = MacosTheme.typography.caption1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MacosTheme.colorScheme.textSecondary,
                                    )
                                    ControlSize(ControlSize.Small) {
                                        SegmentedControl(
                                            options = listOf("Regular", "Tinted"),
                                            selectedIndex = GlassType.entries.indexOf(glassType),
                                            onSelectedIndexChange = { glassType = GlassType.entries[it] },
                                        )
                                    }
                                    Text(
                                        text = "Sidebar Icon Size",
                                        style = MacosTheme.typography.caption1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MacosTheme.colorScheme.textSecondary,
                                    )
                                    val sizeOptions = listOf(ControlSize.Small, ControlSize.Regular, ControlSize.Large)
                                    ControlSize(ControlSize.Small) {
                                        SegmentedControl(
                                            options = listOf("S", "M", "L"),
                                            selectedIndex = sizeOptions.indexOf(sidebarControlSize),
                                            onSelectedIndexChange = { sidebarControlSize = sizeOptions[it] },
                                        )
                                    }
                                }
                            }
                            val uriHandler = LocalUriHandler.current
                            TitleBarButtonGroup {
                                TitleBarGroupButton(onClick = {
                                    uriHandler.openUri("https://github.com/kdroidFilter/compose-macos-26-ui")
                                }) {
                                    Icon(Lucide.Github, modifier = Modifier.size(14.dp))
                                }
                            }
                            TitleBarButtonGroup {
                                TitleBarGroupButton(onClick = {
                                    themeMode = when (themeMode) {
                                        ThemeMode.System -> ThemeMode.Light
                                        ThemeMode.Light -> ThemeMode.Dark
                                        ThemeMode.Dark -> ThemeMode.System
                                    }
                                }) {
                                    Icon(
                                        when (themeMode) {
                                            ThemeMode.System -> Lucide.Monitor
                                            ThemeMode.Light -> LucideSun
                                            ThemeMode.Dark -> LucideMoon
                                        },
                                        modifier = Modifier.size(14.dp),
                                    )
                                }
                            }
                            ToolbarSearchField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                expanded = searchExpanded,
                                onExpandedChange = { searchExpanded = it },
                                expandedWidth = 240.dp,
                                placeholder = "Search components...",
                                onSearch = { query ->
                                    val match = sidebarEntryDefs.firstOrNull {
                                        it.label.lowercase().contains(query.lowercase().trim())
                                    }
                                    if (match != null) {
                                        nav.navigateTo(match.id)
                                        searchQuery = ""
                                        searchExpanded = false
                                    }
                                },
                                suggestions = {
                                    val query = searchQuery.lowercase().trim()
                                    val matches = sidebarEntryDefs.filter {
                                        it.label.lowercase().contains(query)
                                    }
                                    matches.groupBy { it.group }.entries.forEachIndexed { index, (group, items) ->
                                        if (index > 0) SearchSuggestionSeparator()
                                        SearchSuggestionHeader(group)
                                        items.forEach { def ->
                                            SearchSuggestionItem(onClick = {
                                                nav.navigateTo(def.id)
                                                searchQuery = ""
                                                searchExpanded = false
                                            }) {
                                                Text(def.label)
                                            }
                                        }
                                    }
                                },
                            )
                        },
                    )
                },
            ) { contentPadding ->
                // NavDisplay renders the current page from the back stack
                val noAnimation = ContentTransform(
                    targetContentEnter = EnterTransition.None,
                    initialContentExit = ExitTransition.None,
                )
                NavDisplay(
                    backStack = backStack,
                    modifier = Modifier.fillMaxSize(),
                    onBack = { nav.goBack() },
                    transitionSpec = { noAnimation },
                    popTransitionSpec = { noAnimation },
                    predictivePopTransitionSpec = { noAnimation },
                    entryProvider = entryProvider {
                        entry<HomeScreen> {
                            ScrollablePageContent(contentPadding) {
                                HomePage(onNavigate = { nav.navigateTo(it) })
                            }
                        }

                        entry<PageScreen> { screen ->
                            ScrollablePageContent(contentPadding) {
                                PageContent(screen.id, toastState)
                            }
                        }
                    },
                )
            }

            ToastHost(state = toastState)
        }
    }
}

@Composable
private fun ScrollablePageContent(
    contentPadding: androidx.compose.foundation.layout.PaddingValues,
    content: @Composable () -> Unit,
) {
    val contentScrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(contentScrollState)
                .padding(contentPadding)
                .padding(horizontal = 40.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            content()
            Spacer(modifier = Modifier.height(48.dp))
        }
        VerticalScrollbar(
            state = rememberScrollbarState(contentScrollState),
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            showOnEdgeHover = true,
            trackClickBehavior = TrackClickBehavior.Jump,
        )
    }
}

@Composable
private fun PageContent(
    pageId: String,
    toastState: dev.nucleusframework.macoscompose.components.ToastState,
) {
    when (pageId) {
        "getting-started" -> GettingStartedPage()
        "license" -> LicensePage()
        "button" -> ButtonPage()
        "iconbutton" -> IconButtonPage()
        "input" -> InputPage()
        "searchinput" -> SearchInputPage()
        "textarea" -> TextAreaPage()
        "checkbox" -> CheckboxPage()
        "radiobutton" -> RadioButtonPage()
        "switch" -> SwitchPage()
        "combobox" -> ComboBoxPage()
        "multiselect" -> MultiSelectPage()
        "slider" -> SliderPage()
        "circularslider" -> CircularSliderPage()
        "stepper" -> StepperPage()
        "scrollbar" -> ScrollbarPage()
        "groupbox" -> GroupBoxPage()
        "groupedlist" -> GroupedListPage()
        "form" -> FormPage()
        "badge" -> BadgePage()
        "avatar" -> AvatarPage()
        "surface" -> SurfacePage()
        "card" -> CardPage()
        "table" -> TablePage()
        "pagecontrol" -> PageControlPage()
        "progress" -> ProgressPage()
        "skeleton" -> SkeletonPage()
        "alert" -> AlertPage()
        "toast" -> ToastPage(toastState)
        "dialog" -> DialogPage()
        "tooltip" -> TooltipPage()
        "popover" -> PopoverPage()
        "dropdown" -> DropdownMenuPage()
        "contextmenu" -> ContextMenuPage()
        "tabs" -> TabsPage()
        "accordion" -> AccordionPage()
        "sidebar" -> SidebarPage()
        "segmentedcontrol" -> SegmentedControlPage()
        "titlebar" -> TitleBarPage()
        "addressbar" -> AddressBarPage()
        "scaffold" -> ScaffoldPage()
        "popupbutton" -> PopupButtonPage()
        "datepicker" -> DatePickerPage()
        "colorwell" -> ColorWellPage()
        "icons" -> IconsPage()
        "material" -> MaterialPage()
        "controlsize" -> ControlSizePage()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AccentColorPicker(
    selected: AccentColor,
    onSelect: (AccentColor) -> Unit,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        AccentColor.entries.filter { it != AccentColor.Teal }.forEach { color ->
            val isSelected = color == selected
            val displayColor = if (MacosTheme.colorScheme.isDark) color.dark else color.light
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(displayColor, CircleShape)
                    .then(
                        if (isSelected) {
                            Modifier.border(2.dp, MacosTheme.colorScheme.textPrimary, CircleShape)
                        } else {
                            Modifier
                        }
                    )
                    .clickable { onSelect(color) },
            )
        }
    }
}

@Composable
internal expect fun BrowserNavigation(backStack: androidx.compose.runtime.snapshots.SnapshotStateList<AppNavKey>)

@Composable
internal expect fun isSystemDarkMode(): Boolean

@Composable
internal expect fun systemAccentRawColor(): androidx.compose.ui.graphics.Color?
