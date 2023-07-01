package com.kaanf.filtersdrawermenu.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaanf.filtersdrawermenu.data.model.DrawerItem

@Composable
fun CheckableItem(item: DrawerItem.CheckableItem, onItemChecked: (Boolean) -> Unit) {
    var isChecked by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isChecked) Color(0xfffaf0f4) else Color.White)
            .clickable {
                // item.isChecked = !item.isChecked
                isChecked = !isChecked
                onItemChecked.invoke(isChecked)
            }
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (item.color != null) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(item.color, CircleShape)
                    .padding(2.dp)
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )
        }

        Text(
            text = item.title,
            style = TextStyle(
                color = Color(0xff29335c),
                fontSize = 15.sp
            ),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        ItemCheckbox(
            isChecked = isChecked,
            onCheckedChange = {
                // item.isChecked = !item.isChecked
                isChecked = !isChecked
                onItemChecked.invoke(isChecked)
            }
        )
    }
}