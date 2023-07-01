package com.kaanf.filtersdrawermenu.ui.container.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerFooter(buttonTitle: String, onButtonClicked: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(Color.White)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = { },
            shape = RoundedCornerShape(15),
            colors = ButtonDefaults.filledTonalButtonColors(containerColor = Color(0xfffa5373))
        ) {
            Text(text = buttonTitle, style = TextStyle(color = Color.White, fontSize = 15.sp))
        }

        Spacer(
            modifier = Modifier
                .height(40.dp)
        )
    }
}