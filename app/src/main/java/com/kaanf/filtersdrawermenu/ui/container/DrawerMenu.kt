package com.kaanf.filtersdrawermenu.ui.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kaanf.filtersdrawermenu.ui.component.Separator
import com.kaanf.filtersdrawermenu.ui.container.section.DrawerBody
import com.kaanf.filtersdrawermenu.ui.container.section.DrawerFooter
import com.kaanf.filtersdrawermenu.ui.container.section.DrawerHeader
import com.kaanf.filtersdrawermenu.ui.viewmodel.DrawerMenuViewModel
import com.kaanf.filtersdrawermenu.utils.Constants.APPLY
import com.kaanf.filtersdrawermenu.utils.Constants.SHOW_PRODUCTS

@Composable
fun DrawerMenu(navHostController: NavHostController, onCloseClicked: () -> Unit) {
    val viewModel: DrawerMenuViewModel = viewModel()

    val isScreenNavigated by viewModel.isScreenNavigated
    val selectedItems by viewModel.selectedItems

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 110.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(
                    weight = 1f,
                    fill = false
                )
        ) {
            DrawerHeader(
                title = viewModel.currentHeaderTitle.value,
                isAnyItemSelected = false,
                onActionClick = {
                    onCloseClicked.invoke()
                })

            Separator()

            DrawerBody(navHostController = navHostController)
        }

        Separator()

        DrawerFooter(buttonTitle = if (isScreenNavigated) APPLY else SHOW_PRODUCTS)
    }
}