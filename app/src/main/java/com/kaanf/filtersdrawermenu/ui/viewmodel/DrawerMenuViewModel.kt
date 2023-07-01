package com.kaanf.filtersdrawermenu.ui.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.kaanf.filtersdrawermenu.data.model.DrawerItem
import com.kaanf.filtersdrawermenu.utils.Constants.FILTERS

@SuppressLint("MutableCollectionMutableState")
class DrawerMenuViewModel : ViewModel() {
    companion object {
        private const val MAVI = "Mavi"
        private const val SIYAH = "Siyah"
        private const val SARI = "Sarı"

        private const val BEDEN = "Beden"
        private const val FIYAT = "Fiyat"
        private const val RENK = "Renk"
        private const val KUMAS = "Kumaş"
        private const val BALEN = "Balen"
        private const val DOLGU = "Dolgu"
        private const val DENYE = "Denye"
        private const val CINSIYET = "Cinsiyet"
        private const val STOKTA = "Stokta"
        private const val INDIRIMDE = "İndirimde"
        private const val SURDURULEBILIR = "Sürdürülebilir"
    }

    val menuItems: State<List<DrawerItem>> = mutableStateOf(
        listOf(
            DrawerItem.ClickableItem(title = BEDEN),
            DrawerItem.ClickableItem(title = FIYAT),
            DrawerItem.ClickableItem(title = RENK),
            DrawerItem.ClickableItem(title = KUMAS),
            DrawerItem.ClickableItem(title = BALEN),
            DrawerItem.ClickableItem(title = DOLGU),
            DrawerItem.ClickableItem(title = DENYE),
            DrawerItem.ClickableItem(title = CINSIYET),
            DrawerItem.CheckableItem(title = STOKTA),
            DrawerItem.CheckableItem(title = INDIRIMDE),
            DrawerItem.CheckableItem(title = SURDURULEBILIR)
        )
    )

    private var _colorItems = mutableStateListOf<DrawerItem.CheckableItem>()
    val colorItems: State<List<DrawerItem.CheckableItem>> = mutableStateOf(_colorItems)

    var selectedItems = mutableStateOf<MutableList<DrawerItem>>(mutableListOf())

    private var _currentHeaderTitle = mutableStateOf(FILTERS)
    val currentHeaderTitle: State<String>
        get() = _currentHeaderTitle

    private var _isScreenNavigated = mutableStateOf(true)
    val isScreenNavigated: State<Boolean>
        get() = _isScreenNavigated

    private var _isRemoveIconVisible = mutableStateOf(false)
    val isRemoveIconVisible: State<Boolean> = _isRemoveIconVisible

    fun changeHeaderTitle(title: String) {
        _currentHeaderTitle.value = title
    }

    fun toggleNavigationStatus(isNavigated: Boolean) {
        _isScreenNavigated.value = isNavigated
    }

    init {
        loadMenuItems()
        loadColorItems()
    }

    private fun loadMenuItems() {
        /*         */
    }

    private fun loadColorItems() {
        _colorItems.apply {
            add(DrawerItem.CheckableItem(title = MAVI, color = Color.Blue))
            add(DrawerItem.CheckableItem(title = SIYAH, color = Color.Black))
            add(DrawerItem.CheckableItem(title = SARI, color = Color.Yellow))
        }
    }

    fun onItemChecked(item: DrawerItem.CheckableItem, isChecked: Boolean) {
        item.isChecked = isChecked

        if (isChecked)
            selectedItems.value.add(item)
        else
            if (selectedItems.value.contains(item))
                selectedItems.value.remove(item)
    }

}