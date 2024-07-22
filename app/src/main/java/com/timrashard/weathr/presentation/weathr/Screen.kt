package com.timrashard.weathr.presentation.weathr

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")

    object Home : Screen("home_screen")

    object Details : Screen("details_screen")

    object Settings : Screen("settings_screen")
}
