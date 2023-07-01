package com.kaanf.filtersdrawermenu.router

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kaanf.filtersdrawermenu.ui.screen.FiltersScreen
import com.kaanf.filtersdrawermenu.ui.screen.PriceScreen
import com.kaanf.filtersdrawermenu.ui.screen.ColorScreen
import com.kaanf.filtersdrawermenu.ui.viewmodel.DrawerMenuViewModel
import com.kaanf.filtersdrawermenu.utils.Constants.COLOR
import com.kaanf.filtersdrawermenu.utils.Constants.FILTERS
import com.kaanf.filtersdrawermenu.utils.Constants.PRICE

@Composable
fun DrawerNavGraph(navController: NavHostController) {
    val viewModel: DrawerMenuViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.FilterScreen.route,
        builder = {
            composable(route = Screen.FilterScreen.route) {
                viewModel.changeHeaderTitle(FILTERS)
                viewModel.toggleNavigationStatus(isNavigated = false)

                FiltersScreen(items = viewModel.menuItems.value, navigator = navController, onItemChecked = {
                    viewModel.onItemChecked(it, it.isChecked)
                })
            }

            composable(route = Screen.PriceScreen.route) {
                viewModel.changeHeaderTitle(PRICE)
                viewModel.toggleNavigationStatus(isNavigated = true)

                PriceScreen(navigator = navController)
            }

            composable(route = Screen.ColorScreen.route) {
                viewModel.changeHeaderTitle(COLOR)
                viewModel.toggleNavigationStatus(isNavigated = true)

                ColorScreen(items = viewModel.colorItems.value, onItemChecked = {
                    /* onItemChecked handle statement. */
                })
            }
        }
    )
}