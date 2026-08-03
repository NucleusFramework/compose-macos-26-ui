package dev.nucleusframework.macoscompose.sample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.nucleusframework.macoscompose.components.SearchField
import dev.nucleusframework.macoscompose.components.Text
import dev.nucleusframework.macoscompose.icons.Icons
import dev.nucleusframework.macoscompose.icons.Icon
import dev.nucleusframework.macoscompose.icons.SystemIcon
import dev.nucleusframework.macoscompose.icons.extended.IconsExtended
import dev.nucleusframework.macoscompose.icons.extended.Accessibility
import dev.nucleusframework.macoscompose.icons.extended.Activity
import dev.nucleusframework.macoscompose.icons.extended.AlarmClock
import dev.nucleusframework.macoscompose.icons.extended.Archive
import dev.nucleusframework.macoscompose.icons.extended.ArrowDown
import dev.nucleusframework.macoscompose.icons.extended.ArrowDownLeft
import dev.nucleusframework.macoscompose.icons.extended.ArrowDownRight
import dev.nucleusframework.macoscompose.icons.extended.ArrowLeft
import dev.nucleusframework.macoscompose.icons.extended.ArrowRight
import dev.nucleusframework.macoscompose.icons.extended.ArrowUp
import dev.nucleusframework.macoscompose.icons.extended.ArrowUpDown
import dev.nucleusframework.macoscompose.icons.extended.ArrowUpLeft
import dev.nucleusframework.macoscompose.icons.extended.ArrowUpRight
import dev.nucleusframework.macoscompose.icons.extended.AtSign
import dev.nucleusframework.macoscompose.icons.extended.Ban
import dev.nucleusframework.macoscompose.icons.extended.Barcode
import dev.nucleusframework.macoscompose.icons.extended.Battery
import dev.nucleusframework.macoscompose.icons.extended.BatteryCharging
import dev.nucleusframework.macoscompose.icons.extended.BatteryFull
import dev.nucleusframework.macoscompose.icons.extended.BatteryLow
import dev.nucleusframework.macoscompose.icons.extended.Bell
import dev.nucleusframework.macoscompose.icons.extended.BellOff
import dev.nucleusframework.macoscompose.icons.extended.BellRing
import dev.nucleusframework.macoscompose.icons.extended.Bold
import dev.nucleusframework.macoscompose.icons.extended.Book
import dev.nucleusframework.macoscompose.icons.extended.BookOpen
import dev.nucleusframework.macoscompose.icons.extended.Bookmark
import dev.nucleusframework.macoscompose.icons.extended.Bug
import dev.nucleusframework.macoscompose.icons.extended.CalendarCheck
import dev.nucleusframework.macoscompose.icons.extended.CalendarMinus
import dev.nucleusframework.macoscompose.icons.extended.CalendarPlus
import dev.nucleusframework.macoscompose.icons.extended.Camera
import dev.nucleusframework.macoscompose.icons.extended.Car
import dev.nucleusframework.macoscompose.icons.extended.ChartBar
import dev.nucleusframework.macoscompose.icons.extended.ChartLine
import dev.nucleusframework.macoscompose.icons.extended.ChartPie
import dev.nucleusframework.macoscompose.icons.extended.ChevronUp
import dev.nucleusframework.macoscompose.icons.extended.ChevronsDown
import dev.nucleusframework.macoscompose.icons.extended.ChevronsRight
import dev.nucleusframework.macoscompose.icons.extended.ChevronsUp
import dev.nucleusframework.macoscompose.icons.extended.Circle
import dev.nucleusframework.macoscompose.icons.extended.CircleAlert
import dev.nucleusframework.macoscompose.icons.extended.CircleArrowDown
import dev.nucleusframework.macoscompose.icons.extended.CircleArrowLeft
import dev.nucleusframework.macoscompose.icons.extended.CircleArrowRight
import dev.nucleusframework.macoscompose.icons.extended.CircleArrowUp
import dev.nucleusframework.macoscompose.icons.extended.CircleDot
import dev.nucleusframework.macoscompose.icons.extended.CircleMinus
import dev.nucleusframework.macoscompose.icons.extended.CirclePlus
import dev.nucleusframework.macoscompose.icons.extended.Clipboard
import dev.nucleusframework.macoscompose.icons.extended.Clock
import dev.nucleusframework.macoscompose.icons.extended.Cloud
import dev.nucleusframework.macoscompose.icons.extended.CloudDownload
import dev.nucleusframework.macoscompose.icons.extended.CloudLightning
import dev.nucleusframework.macoscompose.icons.extended.CloudOff
import dev.nucleusframework.macoscompose.icons.extended.CloudRain
import dev.nucleusframework.macoscompose.icons.extended.CloudSnow
import dev.nucleusframework.macoscompose.icons.extended.CloudUpload
import dev.nucleusframework.macoscompose.icons.extended.Code
import dev.nucleusframework.macoscompose.icons.extended.Columns2
import dev.nucleusframework.macoscompose.icons.extended.Columns3
import dev.nucleusframework.macoscompose.icons.extended.Compass
import dev.nucleusframework.macoscompose.icons.extended.Cpu
import dev.nucleusframework.macoscompose.icons.extended.CreditCard
import dev.nucleusframework.macoscompose.icons.extended.Crosshair
import dev.nucleusframework.macoscompose.icons.extended.Crown
import dev.nucleusframework.macoscompose.icons.extended.Droplet
import dev.nucleusframework.macoscompose.icons.extended.Dumbbell
import dev.nucleusframework.macoscompose.icons.extended.EllipsisVertical
import dev.nucleusframework.macoscompose.icons.extended.Eraser
import dev.nucleusframework.macoscompose.icons.extended.ExternalLink
import dev.nucleusframework.macoscompose.icons.extended.Eye
import dev.nucleusframework.macoscompose.icons.extended.EyeOff
import dev.nucleusframework.macoscompose.icons.extended.File
import dev.nucleusframework.macoscompose.icons.extended.FilePlus
import dev.nucleusframework.macoscompose.icons.extended.FileText
import dev.nucleusframework.macoscompose.icons.extended.Flag
import dev.nucleusframework.macoscompose.icons.extended.FlagOff
import dev.nucleusframework.macoscompose.icons.extended.Flame
import dev.nucleusframework.macoscompose.icons.extended.FolderMinus
import dev.nucleusframework.macoscompose.icons.extended.FolderOpen
import dev.nucleusframework.macoscompose.icons.extended.FolderPlus
import dev.nucleusframework.macoscompose.icons.extended.Gauge
import dev.nucleusframework.macoscompose.icons.extended.Gift
import dev.nucleusframework.macoscompose.icons.extended.Globe
import dev.nucleusframework.macoscompose.icons.extended.GraduationCap
import dev.nucleusframework.macoscompose.icons.extended.Grid2x2
import dev.nucleusframework.macoscompose.icons.extended.Grid3x3
import dev.nucleusframework.macoscompose.icons.extended.Hammer
import dev.nucleusframework.macoscompose.icons.extended.Hand
import dev.nucleusframework.macoscompose.icons.extended.HardDrive
import dev.nucleusframework.macoscompose.icons.extended.Hash
import dev.nucleusframework.macoscompose.icons.extended.Headphones
import dev.nucleusframework.macoscompose.icons.extended.HeartOff
import dev.nucleusframework.macoscompose.icons.extended.Hourglass
import dev.nucleusframework.macoscompose.icons.extended.Infinity
import dev.nucleusframework.macoscompose.icons.extended.Italic
import dev.nucleusframework.macoscompose.icons.extended.Key
import dev.nucleusframework.macoscompose.icons.extended.KeyRound
import dev.nucleusframework.macoscompose.icons.extended.Keyboard
import dev.nucleusframework.macoscompose.icons.extended.Laptop
import dev.nucleusframework.macoscompose.icons.extended.Layers
import dev.nucleusframework.macoscompose.icons.extended.Leaf
import dev.nucleusframework.macoscompose.icons.extended.Lightbulb
import dev.nucleusframework.macoscompose.icons.extended.LightbulbOff
import dev.nucleusframework.macoscompose.icons.extended.Link
import dev.nucleusframework.macoscompose.icons.extended.ListCheck
import dev.nucleusframework.macoscompose.icons.extended.ListOrdered
import dev.nucleusframework.macoscompose.icons.extended.Locate
import dev.nucleusframework.macoscompose.icons.extended.Lock
import dev.nucleusframework.macoscompose.icons.extended.LockOpen
import dev.nucleusframework.macoscompose.icons.extended.LogIn
import dev.nucleusframework.macoscompose.icons.extended.Mail
import dev.nucleusframework.macoscompose.icons.extended.MailOpen
import dev.nucleusframework.macoscompose.icons.extended.Map
import dev.nucleusframework.macoscompose.icons.extended.MapPin
import dev.nucleusframework.macoscompose.icons.extended.Maximize
import dev.nucleusframework.macoscompose.icons.extended.Menu
import dev.nucleusframework.macoscompose.icons.extended.MessageCircle
import dev.nucleusframework.macoscompose.icons.extended.MessageSquare
import dev.nucleusframework.macoscompose.icons.extended.Mic
import dev.nucleusframework.macoscompose.icons.extended.MicOff
import dev.nucleusframework.macoscompose.icons.extended.Minimize
import dev.nucleusframework.macoscompose.icons.extended.Minus
import dev.nucleusframework.macoscompose.icons.extended.Monitor
import dev.nucleusframework.macoscompose.icons.extended.Music
import dev.nucleusframework.macoscompose.icons.extended.Navigation2
import dev.nucleusframework.macoscompose.icons.extended.Newspaper
import dev.nucleusframework.macoscompose.icons.extended.Paintbrush
import dev.nucleusframework.macoscompose.icons.extended.Palette
import dev.nucleusframework.macoscompose.icons.extended.PanelRight
import dev.nucleusframework.macoscompose.icons.extended.Pause
import dev.nucleusframework.macoscompose.icons.extended.Percent
import dev.nucleusframework.macoscompose.icons.extended.Phone
import dev.nucleusframework.macoscompose.icons.extended.PhoneCall
import dev.nucleusframework.macoscompose.icons.extended.PhoneIncoming
import dev.nucleusframework.macoscompose.icons.extended.PhoneOff
import dev.nucleusframework.macoscompose.icons.extended.PhoneOutgoing
import dev.nucleusframework.macoscompose.icons.extended.Pill
import dev.nucleusframework.macoscompose.icons.extended.Pin
import dev.nucleusframework.macoscompose.icons.extended.PinOff
import dev.nucleusframework.macoscompose.icons.extended.Pipette
import dev.nucleusframework.macoscompose.icons.extended.Plane
import dev.nucleusframework.macoscompose.icons.extended.Play
import dev.nucleusframework.macoscompose.icons.extended.Power
import dev.nucleusframework.macoscompose.icons.extended.Printer
import dev.nucleusframework.macoscompose.icons.extended.QrCode
import dev.nucleusframework.macoscompose.icons.extended.Redo
import dev.nucleusframework.macoscompose.icons.extended.RefreshCcw
import dev.nucleusframework.macoscompose.icons.extended.RefreshCw
import dev.nucleusframework.macoscompose.icons.extended.Repeat
import dev.nucleusframework.macoscompose.icons.extended.Repeat1
import dev.nucleusframework.macoscompose.icons.extended.Rocket
import dev.nucleusframework.macoscompose.icons.extended.Rows2
import dev.nucleusframework.macoscompose.icons.extended.Ruler
import dev.nucleusframework.macoscompose.icons.extended.Save
import dev.nucleusframework.macoscompose.icons.extended.Scale
import dev.nucleusframework.macoscompose.icons.extended.Scan
import dev.nucleusframework.macoscompose.icons.extended.Scissors
import dev.nucleusframework.macoscompose.icons.extended.Send
import dev.nucleusframework.macoscompose.icons.extended.Share
import dev.nucleusframework.macoscompose.icons.extended.Shield
import dev.nucleusframework.macoscompose.icons.extended.ShieldAlert
import dev.nucleusframework.macoscompose.icons.extended.ShieldCheck
import dev.nucleusframework.macoscompose.icons.extended.ShieldOff
import dev.nucleusframework.macoscompose.icons.extended.ShoppingBag
import dev.nucleusframework.macoscompose.icons.extended.ShoppingCart
import dev.nucleusframework.macoscompose.icons.extended.Shuffle
import dev.nucleusframework.macoscompose.icons.extended.SkipBack
import dev.nucleusframework.macoscompose.icons.extended.SkipForward
import dev.nucleusframework.macoscompose.icons.extended.SlidersHorizontal
import dev.nucleusframework.macoscompose.icons.extended.Smartphone
import dev.nucleusframework.macoscompose.icons.extended.Snowflake
import dev.nucleusframework.macoscompose.icons.extended.Sparkles
import dev.nucleusframework.macoscompose.icons.extended.Square
import dev.nucleusframework.macoscompose.icons.extended.StarHalf
import dev.nucleusframework.macoscompose.icons.extended.Stethoscope
import dev.nucleusframework.macoscompose.icons.extended.Strikethrough
import dev.nucleusframework.macoscompose.icons.extended.Sunrise
import dev.nucleusframework.macoscompose.icons.extended.Sunset
import dev.nucleusframework.macoscompose.icons.extended.Syringe
import dev.nucleusframework.macoscompose.icons.extended.Table
import dev.nucleusframework.macoscompose.icons.extended.Tablet
import dev.nucleusframework.macoscompose.icons.extended.Target
import dev.nucleusframework.macoscompose.icons.extended.Terminal
import dev.nucleusframework.macoscompose.icons.extended.ThumbsDown
import dev.nucleusframework.macoscompose.icons.extended.ThumbsUp
import dev.nucleusframework.macoscompose.icons.extended.Timer
import dev.nucleusframework.macoscompose.icons.extended.Trash
import dev.nucleusframework.macoscompose.icons.extended.TrendingDown
import dev.nucleusframework.macoscompose.icons.extended.TrendingUp
import dev.nucleusframework.macoscompose.icons.extended.Triangle
import dev.nucleusframework.macoscompose.icons.extended.Trophy
import dev.nucleusframework.macoscompose.icons.extended.Tv
import dev.nucleusframework.macoscompose.icons.extended.Type
import dev.nucleusframework.macoscompose.icons.extended.Underline
import dev.nucleusframework.macoscompose.icons.extended.Undo
import dev.nucleusframework.macoscompose.icons.extended.User
import dev.nucleusframework.macoscompose.icons.extended.UserMinus
import dev.nucleusframework.macoscompose.icons.extended.UserPlus
import dev.nucleusframework.macoscompose.icons.extended.UserRound
import dev.nucleusframework.macoscompose.icons.extended.Users
import dev.nucleusframework.macoscompose.icons.extended.Video
import dev.nucleusframework.macoscompose.icons.extended.VideoOff
import dev.nucleusframework.macoscompose.icons.extended.Volume
import dev.nucleusframework.macoscompose.icons.extended.Volume1
import dev.nucleusframework.macoscompose.icons.extended.Volume2
import dev.nucleusframework.macoscompose.icons.extended.VolumeOff
import dev.nucleusframework.macoscompose.icons.extended.VolumeX
import dev.nucleusframework.macoscompose.icons.extended.WandSparkles
import dev.nucleusframework.macoscompose.icons.extended.Wifi
import dev.nucleusframework.macoscompose.icons.extended.WifiOff
import dev.nucleusframework.macoscompose.icons.extended.Wind
import dev.nucleusframework.macoscompose.icons.extended.Wrench
import dev.nucleusframework.macoscompose.icons.extended.Zap
import dev.nucleusframework.macoscompose.icons.extended.ZapOff
import dev.nucleusframework.macoscompose.icons.extended.ZoomIn
import dev.nucleusframework.macoscompose.icons.extended.ZoomOut
import dev.nucleusframework.macoscompose.sample.gallery.GalleryPage
import dev.nucleusframework.macoscompose.sample.gallery.SectionHeader
import dev.nucleusframework.macoscompose.theme.MacosTheme

private data class IconEntry(val name: String, val icon: SystemIcon)

private val coreIcons = listOf(
    IconEntry("Plus", Icons.Plus),
    IconEntry("Settings", Icons.Settings),
    IconEntry("Heart", Icons.Heart),
    IconEntry("X", Icons.X),
    IconEntry("Check", Icons.Check),
    IconEntry("Trash2", Icons.Trash2),
    IconEntry("Download", Icons.Download),
    IconEntry("Share2", Icons.Share2),
    IconEntry("Sun", Icons.Sun),
    IconEntry("Moon", Icons.Moon),
    IconEntry("Search", Icons.Search),
    IconEntry("ChevronDown", Icons.ChevronDown),
    IconEntry("Info", Icons.Info),
    IconEntry("TriangleAlert", Icons.TriangleAlert),
    IconEntry("CircleCheck", Icons.CircleCheck),
    IconEntry("CircleX", Icons.CircleX),
    IconEntry("Calendar", Icons.Calendar),
    IconEntry("ChevronLeft", Icons.ChevronLeft),
    IconEntry("Copy", Icons.Copy),
    IconEntry("Upload", Icons.Upload),
    IconEntry("ImageIcon", Icons.ImageIcon),
    IconEntry("Star", Icons.Star),
    IconEntry("StarOff", Icons.StarOff),
    IconEntry("ArrowLeftRight", Icons.ArrowLeftRight),
    IconEntry("ChevronRight", Icons.ChevronRight),
    IconEntry("ChevronsLeft", Icons.ChevronsLeft),
    IconEntry("LogOut", Icons.LogOut),
    IconEntry("Home", Icons.Home),
    IconEntry("Folder", Icons.Folder),
    IconEntry("BarChart3", Icons.BarChart3),
    IconEntry("ChevronsUpDown", Icons.ChevronsUpDown),
    IconEntry("PanelLeft", Icons.PanelLeft),
    IconEntry("LayoutGrid", Icons.LayoutGrid),
    IconEntry("List", Icons.List),
    IconEntry("Ellipsis", Icons.Ellipsis),
    IconEntry("Tag", Icons.Tag),
)

private val extendedIcons = listOf(
    // Navigation
    IconEntry("ArrowUp", IconsExtended.ArrowUp),
    IconEntry("ArrowDown", IconsExtended.ArrowDown),
    IconEntry("ArrowLeft", IconsExtended.ArrowLeft),
    IconEntry("ArrowRight", IconsExtended.ArrowRight),
    IconEntry("ArrowUpLeft", IconsExtended.ArrowUpLeft),
    IconEntry("ArrowUpRight", IconsExtended.ArrowUpRight),
    IconEntry("ArrowDownLeft", IconsExtended.ArrowDownLeft),
    IconEntry("ArrowDownRight", IconsExtended.ArrowDownRight),
    IconEntry("ArrowUpDown", IconsExtended.ArrowUpDown),
    IconEntry("ChevronUp", IconsExtended.ChevronUp),
    IconEntry("ChevronsUp", IconsExtended.ChevronsUp),
    IconEntry("ChevronsDown", IconsExtended.ChevronsDown),
    IconEntry("ChevronsRight", IconsExtended.ChevronsRight),
    // Actions
    IconEntry("Minus", IconsExtended.Minus),
    IconEntry("ZoomIn", IconsExtended.ZoomIn),
    IconEntry("ZoomOut", IconsExtended.ZoomOut),
    IconEntry("Trash", IconsExtended.Trash),
    IconEntry("Share", IconsExtended.Share),
    IconEntry("Clipboard", IconsExtended.Clipboard),
    IconEntry("Scissors", IconsExtended.Scissors),
    IconEntry("RefreshCw", IconsExtended.RefreshCw),
    IconEntry("RefreshCcw", IconsExtended.RefreshCcw),
    IconEntry("Undo", IconsExtended.Undo),
    IconEntry("Redo", IconsExtended.Redo),
    IconEntry("Save", IconsExtended.Save),
    IconEntry("ExternalLink", IconsExtended.ExternalLink),
    IconEntry("Link", IconsExtended.Link),
    IconEntry("LogIn", IconsExtended.LogIn),
    // Status
    IconEntry("HeartOff", IconsExtended.HeartOff),
    IconEntry("StarHalf", IconsExtended.StarHalf),
    IconEntry("Eye", IconsExtended.Eye),
    IconEntry("EyeOff", IconsExtended.EyeOff),
    IconEntry("Bell", IconsExtended.Bell),
    IconEntry("BellOff", IconsExtended.BellOff),
    IconEntry("BellRing", IconsExtended.BellRing),
    IconEntry("Bookmark", IconsExtended.Bookmark),
    IconEntry("Flag", IconsExtended.Flag),
    IconEntry("FlagOff", IconsExtended.FlagOff),
    IconEntry("Ban", IconsExtended.Ban),
    // Weather
    IconEntry("Cloud", IconsExtended.Cloud),
    IconEntry("CloudRain", IconsExtended.CloudRain),
    IconEntry("CloudSnow", IconsExtended.CloudSnow),
    IconEntry("CloudLightning", IconsExtended.CloudLightning),
    IconEntry("CloudOff", IconsExtended.CloudOff),
    IconEntry("CloudDownload", IconsExtended.CloudDownload),
    IconEntry("CloudUpload", IconsExtended.CloudUpload),
    IconEntry("Snowflake", IconsExtended.Snowflake),
    IconEntry("Wind", IconsExtended.Wind),
    IconEntry("Sunrise", IconsExtended.Sunrise),
    IconEntry("Sunset", IconsExtended.Sunset),
    IconEntry("Droplet", IconsExtended.Droplet),
    // Calendar & Time
    IconEntry("CalendarPlus", IconsExtended.CalendarPlus),
    IconEntry("CalendarMinus", IconsExtended.CalendarMinus),
    IconEntry("CalendarCheck", IconsExtended.CalendarCheck),
    IconEntry("Clock", IconsExtended.Clock),
    IconEntry("Timer", IconsExtended.Timer),
    IconEntry("Hourglass", IconsExtended.Hourglass),
    IconEntry("AlarmClock", IconsExtended.AlarmClock),
    // Communication
    IconEntry("Mail", IconsExtended.Mail),
    IconEntry("MailOpen", IconsExtended.MailOpen),
    IconEntry("Phone", IconsExtended.Phone),
    IconEntry("PhoneCall", IconsExtended.PhoneCall),
    IconEntry("PhoneOff", IconsExtended.PhoneOff),
    IconEntry("PhoneIncoming", IconsExtended.PhoneIncoming),
    IconEntry("PhoneOutgoing", IconsExtended.PhoneOutgoing),
    IconEntry("MessageCircle", IconsExtended.MessageCircle),
    IconEntry("MessageSquare", IconsExtended.MessageSquare),
    IconEntry("Send", IconsExtended.Send),
    // Media
    IconEntry("Play", IconsExtended.Play),
    IconEntry("Pause", IconsExtended.Pause),
    IconEntry("SkipForward", IconsExtended.SkipForward),
    IconEntry("SkipBack", IconsExtended.SkipBack),
    IconEntry("Volume", IconsExtended.Volume),
    IconEntry("Volume1", IconsExtended.Volume1),
    IconEntry("Volume2", IconsExtended.Volume2),
    IconEntry("VolumeX", IconsExtended.VolumeX),
    IconEntry("VolumeOff", IconsExtended.VolumeOff),
    IconEntry("Mic", IconsExtended.Mic),
    IconEntry("MicOff", IconsExtended.MicOff),
    IconEntry("Camera", IconsExtended.Camera),
    IconEntry("Video", IconsExtended.Video),
    IconEntry("VideoOff", IconsExtended.VideoOff),
    IconEntry("Music", IconsExtended.Music),
    IconEntry("Headphones", IconsExtended.Headphones),
    // Files & Folders
    IconEntry("File", IconsExtended.File),
    IconEntry("FileText", IconsExtended.FileText),
    IconEntry("FilePlus", IconsExtended.FilePlus),
    IconEntry("FolderOpen", IconsExtended.FolderOpen),
    IconEntry("FolderPlus", IconsExtended.FolderPlus),
    IconEntry("FolderMinus", IconsExtended.FolderMinus),
    IconEntry("Archive", IconsExtended.Archive),
    // Devices
    IconEntry("Monitor", IconsExtended.Monitor),
    IconEntry("Laptop", IconsExtended.Laptop),
    IconEntry("Smartphone", IconsExtended.Smartphone),
    IconEntry("Tablet", IconsExtended.Tablet),
    IconEntry("Keyboard", IconsExtended.Keyboard),
    IconEntry("Printer", IconsExtended.Printer),
    IconEntry("HardDrive", IconsExtended.HardDrive),
    IconEntry("Cpu", IconsExtended.Cpu),
    IconEntry("Wifi", IconsExtended.Wifi),
    IconEntry("WifiOff", IconsExtended.WifiOff),
    IconEntry("Battery", IconsExtended.Battery),
    IconEntry("BatteryCharging", IconsExtended.BatteryCharging),
    IconEntry("BatteryFull", IconsExtended.BatteryFull),
    IconEntry("BatteryLow", IconsExtended.BatteryLow),
    IconEntry("Tv", IconsExtended.Tv),
    // Shapes & Status
    IconEntry("Circle", IconsExtended.Circle),
    IconEntry("CircleAlert", IconsExtended.CircleAlert),
    IconEntry("CirclePlus", IconsExtended.CirclePlus),
    IconEntry("CircleMinus", IconsExtended.CircleMinus),
    IconEntry("CircleDot", IconsExtended.CircleDot),
    IconEntry("CircleArrowUp", IconsExtended.CircleArrowUp),
    IconEntry("CircleArrowDown", IconsExtended.CircleArrowDown),
    IconEntry("CircleArrowLeft", IconsExtended.CircleArrowLeft),
    IconEntry("CircleArrowRight", IconsExtended.CircleArrowRight),
    IconEntry("Square", IconsExtended.Square),
    IconEntry("Triangle", IconsExtended.Triangle),
    // Security
    IconEntry("Shield", IconsExtended.Shield),
    IconEntry("ShieldCheck", IconsExtended.ShieldCheck),
    IconEntry("ShieldAlert", IconsExtended.ShieldAlert),
    IconEntry("ShieldOff", IconsExtended.ShieldOff),
    IconEntry("Lock", IconsExtended.Lock),
    IconEntry("LockOpen", IconsExtended.LockOpen),
    IconEntry("Key", IconsExtended.Key),
    IconEntry("KeyRound", IconsExtended.KeyRound),
    // Layout
    IconEntry("PanelRight", IconsExtended.PanelRight),
    IconEntry("Grid2x2", IconsExtended.Grid2x2),
    IconEntry("Grid3x3", IconsExtended.Grid3x3),
    IconEntry("ListOrdered", IconsExtended.ListOrdered),
    IconEntry("ListCheck", IconsExtended.ListCheck),
    IconEntry("Menu", IconsExtended.Menu),
    IconEntry("EllipsisVertical", IconsExtended.EllipsisVertical),
    IconEntry("Columns2", IconsExtended.Columns2),
    IconEntry("Columns3", IconsExtended.Columns3),
    IconEntry("Rows2", IconsExtended.Rows2),
    IconEntry("Table", IconsExtended.Table),
    IconEntry("SlidersHorizontal", IconsExtended.SlidersHorizontal),
    IconEntry("Layers", IconsExtended.Layers),
    // Charts
    IconEntry("ChartBar", IconsExtended.ChartBar),
    IconEntry("ChartLine", IconsExtended.ChartLine),
    IconEntry("ChartPie", IconsExtended.ChartPie),
    IconEntry("TrendingUp", IconsExtended.TrendingUp),
    IconEntry("TrendingDown", IconsExtended.TrendingDown),
    IconEntry("Activity", IconsExtended.Activity),
    IconEntry("Gauge", IconsExtended.Gauge),
    // People
    IconEntry("User", IconsExtended.User),
    IconEntry("UserPlus", IconsExtended.UserPlus),
    IconEntry("UserMinus", IconsExtended.UserMinus),
    IconEntry("Users", IconsExtended.Users),
    IconEntry("UserRound", IconsExtended.UserRound),
    IconEntry("Accessibility", IconsExtended.Accessibility),
    // Commerce
    IconEntry("ShoppingCart", IconsExtended.ShoppingCart),
    IconEntry("ShoppingBag", IconsExtended.ShoppingBag),
    IconEntry("CreditCard", IconsExtended.CreditCard),
    IconEntry("Gift", IconsExtended.Gift),
    IconEntry("Percent", IconsExtended.Percent),
    // Transport & Location
    IconEntry("Car", IconsExtended.Car),
    IconEntry("Plane", IconsExtended.Plane),
    IconEntry("Rocket", IconsExtended.Rocket),
    IconEntry("Map", IconsExtended.Map),
    IconEntry("MapPin", IconsExtended.MapPin),
    IconEntry("Globe", IconsExtended.Globe),
    IconEntry("Compass", IconsExtended.Compass),
    IconEntry("Navigation2", IconsExtended.Navigation2),
    IconEntry("Locate", IconsExtended.Locate),
    // Tools
    IconEntry("Wrench", IconsExtended.Wrench),
    IconEntry("Hammer", IconsExtended.Hammer),
    IconEntry("Paintbrush", IconsExtended.Paintbrush),
    IconEntry("Eraser", IconsExtended.Eraser),
    IconEntry("Ruler", IconsExtended.Ruler),
    IconEntry("Pipette", IconsExtended.Pipette),
    IconEntry("Palette", IconsExtended.Palette),
    IconEntry("Scan", IconsExtended.Scan),
    IconEntry("QrCode", IconsExtended.QrCode),
    IconEntry("Barcode", IconsExtended.Barcode),
    IconEntry("Pin", IconsExtended.Pin),
    IconEntry("PinOff", IconsExtended.PinOff),
    // Energy & Nature
    IconEntry("Lightbulb", IconsExtended.Lightbulb),
    IconEntry("LightbulbOff", IconsExtended.LightbulbOff),
    IconEntry("Zap", IconsExtended.Zap),
    IconEntry("ZapOff", IconsExtended.ZapOff),
    IconEntry("Power", IconsExtended.Power),
    IconEntry("Flame", IconsExtended.Flame),
    IconEntry("Leaf", IconsExtended.Leaf),
    // Text Formatting
    IconEntry("Bold", IconsExtended.Bold),
    IconEntry("Italic", IconsExtended.Italic),
    IconEntry("Underline", IconsExtended.Underline),
    IconEntry("Strikethrough", IconsExtended.Strikethrough),
    IconEntry("Type", IconsExtended.Type),
    IconEntry("Code", IconsExtended.Code),
    IconEntry("Hash", IconsExtended.Hash),
    IconEntry("AtSign", IconsExtended.AtSign),
    // Knowledge
    IconEntry("Book", IconsExtended.Book),
    IconEntry("BookOpen", IconsExtended.BookOpen),
    IconEntry("GraduationCap", IconsExtended.GraduationCap),
    IconEntry("Newspaper", IconsExtended.Newspaper),
    // Health & Fitness
    IconEntry("Stethoscope", IconsExtended.Stethoscope),
    IconEntry("Pill", IconsExtended.Pill),
    IconEntry("Syringe", IconsExtended.Syringe),
    IconEntry("Dumbbell", IconsExtended.Dumbbell),
    // Misc
    IconEntry("Bug", IconsExtended.Bug),
    IconEntry("Terminal", IconsExtended.Terminal),
    IconEntry("Target", IconsExtended.Target),
    IconEntry("Crosshair", IconsExtended.Crosshair),
    IconEntry("Hand", IconsExtended.Hand),
    IconEntry("ThumbsUp", IconsExtended.ThumbsUp),
    IconEntry("ThumbsDown", IconsExtended.ThumbsDown),
    IconEntry("Sparkles", IconsExtended.Sparkles),
    IconEntry("WandSparkles", IconsExtended.WandSparkles),
    IconEntry("Trophy", IconsExtended.Trophy),
    IconEntry("Crown", IconsExtended.Crown),
    IconEntry("Scale", IconsExtended.Scale),
    IconEntry("Infinity", IconsExtended.Infinity),
    IconEntry("Repeat", IconsExtended.Repeat),
    IconEntry("Repeat1", IconsExtended.Repeat1),
    IconEntry("Shuffle", IconsExtended.Shuffle),
    IconEntry("Maximize", IconsExtended.Maximize),
    IconEntry("Minimize", IconsExtended.Minimize),
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IconGrid(icons: List<IconEntry>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        icons.forEach { entry ->
            Column(
                modifier = Modifier.width(80.dp).padding(vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    icon = entry.icon,
                    contentDescription = entry.name,
                    modifier = Modifier.size(24.dp),
                )
                Text(
                    text = entry.name,
                    style = MacosTheme.typography.caption2,
                    color = MacosTheme.colorScheme.textSecondary,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
internal fun IconsPage() {
    var searchQuery by remember { mutableStateOf("") }
    val allIcons = remember { coreIcons + extendedIcons }
    val filteredCore = remember(searchQuery) {
        if (searchQuery.isBlank()) coreIcons
        else coreIcons.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }
    val filteredExtended = remember(searchQuery) {
        if (searchQuery.isBlank()) extendedIcons
        else extendedIcons.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }
    val totalCount = allIcons.size
    val matchCount = filteredCore.size + filteredExtended.size

    GalleryPage("Icons", "All available macOS icons with SF Symbol mapping.") {
        Text(
            text = "On Apple platforms (macOS and iOS), icons are rendered using native SF Symbols. " +
                "On all other platforms (Android, Web, Windows, Linux), Lucide vector icons are used as fallback.",
            style = MacosTheme.typography.callout,
            color = MacosTheme.colorScheme.textSecondary,
        )

        SearchField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = "Search icons ($totalCount available)...",
            modifier = Modifier.fillMaxWidth(),
        )

        if (filteredCore.isNotEmpty()) {
            SectionHeader("Core Icons (${filteredCore.size})")
            IconGrid(filteredCore)
        }

        if (filteredExtended.isNotEmpty()) {
            SectionHeader("Extended Icons (${filteredExtended.size})")
            IconGrid(filteredExtended)
        }

        if (matchCount == 0) {
            Text(
                text = "No icons matching \"$searchQuery\"",
                style = MacosTheme.typography.body,
                color = MacosTheme.colorScheme.textTertiary,
            )
        }
    }
}
