package com.kaanf.filtersdrawermenu.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kaanf.filtersdrawermenu.ui.component.PriceRanger

@Composable
fun PriceScreen(navigator: NavHostController = rememberNavController()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        PriceRanger(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            rangeColor = Color(0xfffa5373),
            backColor = Color(203, 225, 246),
            barHeight = 6.dp,
            circleRadius = 16.dp,
            progress1InitialValue = 0.0f,
            progress2InitialValue = 1.0f,
            tooltipSpacing = 10.dp,
            tooltipWidth = 40.dp,
            tooltipHeight = 30.dp,
            cornerRadius = CornerRadius(32f, 32f)
        ) { _, _ ->
            
        }
    }
}

@Composable
@Preview
fun PreviewPriceScreen() {
    PriceScreen()
}