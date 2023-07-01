package com.kaanf.filtersdrawermenu.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Separator() {
    Divider(
        color = Color(0xfffaf0f4),
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )
}