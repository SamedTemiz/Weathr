package com.timrashard.weathr.presentation.weathr

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object Details : Screen("details")

    object Settings : Screen("settings")
}
