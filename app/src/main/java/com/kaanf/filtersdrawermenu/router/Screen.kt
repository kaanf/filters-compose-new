package com.kaanf.filtersdrawermenu.router

sealed class Screen(val route: String) {
    object FilterScreen : Screen(route = Route.Filter)
    object PriceScreen : Screen(route = Route.Price)
    object ColorScreen : Screen(route = Route.Color)
    object SizeScreen : Screen(route = Route.Size)
}