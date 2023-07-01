package com.kaanf.filtersdrawermenu.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaanf.filtersdrawermenu.R
import com.kaanf.filtersdrawermenu.data.model.DrawerItem

@Composable
fun ClickableItem(item: DrawerItem.ClickableItem, onItemClicked: (DrawerItem.ClickableItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
            .clickable {
                onItemClicked.invoke(item)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.title, style = TextStyle(color = Color(0xff29335c), fontSize = 15.sp), modifier = Modifier.padding(8.dp))

        Image(
            painterResource(R.drawable.icons_keyboard_arrow),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(20.dp)
                .padding(end = 4.dp)
        )
    }
}