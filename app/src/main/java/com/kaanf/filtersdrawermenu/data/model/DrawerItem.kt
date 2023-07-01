package com.kaanf.filtersdrawermenu.data.model

import androidx.compose.ui.graphics.Color

sealed class DrawerItem {
    data class CheckableItem(val title: String, var isChecked: Boolean = false, val color: Color? = null) : DrawerItem()
    data class ClickableItem(val title: String) : DrawerItem()
}
