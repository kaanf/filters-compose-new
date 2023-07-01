package com.kaanf.filtersdrawermenu.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ItemCheckbox(isChecked: Boolean, onCheckedChange: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.25.dp, color = Color(0xfffa5373))
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isChecked)
                        Color(0xfffaf0f4)
                    else
                        Color.White)
                .size(20.dp)
                .clickable {
                    onCheckedChange.invoke()
                },
            contentAlignment = Alignment.Center
        ) {
            if (isChecked)
                Icon(Icons.Default.Check, contentDescription = "", tint = Color(0xfffa5373), modifier = Modifier.padding(4.dp))
        }
    }
}