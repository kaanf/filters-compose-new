package com.kaanf.filtersdrawermenu.ui.container.section

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kaanf.filtersdrawermenu.router.DrawerNavGraph

@Composable
fun DrawerBody(navHostController: NavHostController) {
    DrawerNavGraph(navController = navHostController)
}
