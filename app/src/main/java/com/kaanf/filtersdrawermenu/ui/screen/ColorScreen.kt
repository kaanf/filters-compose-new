package com.kaanf.filtersdrawermenu.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kaanf.filtersdrawermenu.data.model.DrawerItem
import com.kaanf.filtersdrawermenu.ui.component.CheckableItem

@Composable
fun ColorScreen(
    items: List<DrawerItem.CheckableItem>,
    onItemChecked: (DrawerItem.CheckableItem) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(items) { index, item ->
            CheckableItem(
                item = item,
                onItemChecked = {
                    onItemChecked.invoke(item)
                })

            if (index < items.lastIndex)
                Divider(color = Color.White, thickness = 1.dp)
        }
    }
}
