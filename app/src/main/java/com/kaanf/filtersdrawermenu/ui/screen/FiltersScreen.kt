package com.kaanf.filtersdrawermenu.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kaanf.filtersdrawermenu.router.Screen
import com.kaanf.filtersdrawermenu.data.model.DrawerItem
import com.kaanf.filtersdrawermenu.ui.component.CheckableItem
import com.kaanf.filtersdrawermenu.ui.component.ClickableItem
import com.kaanf.filtersdrawermenu.ui.viewmodel.DrawerMenuViewModel

@Composable
fun FiltersScreen(
    items: List<DrawerItem>,
    onItemChecked: (DrawerItem.CheckableItem) -> Unit,
    navigator: NavHostController
) {
    val viewModel: DrawerMenuViewModel = viewModel()
    // val menuItems by remember { viewModel.menuItems }

    LazyColumn {
        itemsIndexed(items) { index, item ->
            when (item) {
                is DrawerItem.CheckableItem -> {
                    CheckableItem(item, onItemChecked = {
                        onItemChecked.invoke(item)
                    })
                }
                is DrawerItem.ClickableItem -> {
                    ClickableItem(item, onItemClicked = {
                        if (item.title == "Fiyat")
                            navigator.navigate(Screen.PriceScreen.route)
                        else if (item.title == "Renk")
                            navigator.navigate(Screen.ColorScreen.route)
                    })
                }
            }

            if (index < items.lastIndex)
                Divider(color = Color.White, thickness = 1.dp)
        }
    }
}
