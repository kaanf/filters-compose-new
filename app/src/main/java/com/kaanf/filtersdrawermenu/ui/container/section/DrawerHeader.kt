package com.kaanf.filtersdrawermenu.ui.container.section

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerHeader(title: String, onActionClick: () -> Unit = {}, isAnyItemSelected: Boolean) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .background(Color.White),
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
        title = {
            Text(text = title, style = TextStyle(color = Color(0xff81819b), fontSize = 18.sp))
        },
        navigationIcon = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Icon for close drawer menu.",
                    tint = Color(0xff29335c)
                )
            }
        },
        actions = {
            if (isAnyItemSelected) {
                IconButton(onClick = onActionClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove selected filters.",
                        tint = Color(0xfffa5373)
                    )
                }
            }
        },
    )
}