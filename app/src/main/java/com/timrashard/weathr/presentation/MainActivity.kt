package com.timrashard.weathr.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.timrashard.weathr.presentation.theme.WeathrTheme
import com.timrashard.weathr.presentation.weathr.Screen
import com.timrashard.weathr.presentation.weathr.WeatherViewModel
import com.timrashard.weathr.presentation.weathr.details.DetailsScreen
import com.timrashard.weathr.presentation.weathr.home.HomeScreen
import com.timrashard.weathr.presentation.weathr.settings.SettingsScreen
import com.timrashard.weathr.utils.DataUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataUtils.init(this) // KAldırcaz bunu TODO

        setContent {
            WeathrTheme {
                val navController = rememberNavController()
                val viewModel: WeatherViewModel = hiltViewModel()

                WeatherApp(navController, viewModel)
            }
        }
    }
}

@Composable
fun WeatherApp(navController: NavHostController, weatherViewModel: WeatherViewModel) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, viewModel = weatherViewModel)
        }

        composable(Screen.Details.route) {
            DetailsScreen(navController = navController, viewModel = weatherViewModel)
        }

        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}
