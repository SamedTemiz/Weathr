package com.timrashard.weathr.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.timrashard.weathr.R
import com.timrashard.weathr.domain.repository.ConnectivityRepository
import com.timrashard.weathr.presentation.components.AnimatedShimmer
import com.timrashard.weathr.presentation.components.NetworkStatusDialog
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
        DataUtils.init(applicationContext) // Örnek verileri kullanmak için geçici, kaldır sonradan TODO

        startApp()
    }

    private fun startApp() {
        setContent {
            val navController = rememberNavController()
            val viewModel: WeatherViewModel = hiltViewModel()

            val networkState by viewModel.networkStatus.collectAsState()

            WeathrTheme {
                Surface(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    when (networkState) {
                        ConnectivityRepository.NetworkStatus.Available -> {
                            WeatherApp(navController, viewModel)
                        }

                        ConnectivityRepository.NetworkStatus.Lost -> {
                            NetworkStatusDialog(
                                title = stringResource(id = R.string.network_unavailable_title),
                                message = stringResource(id = R.string.network_unavailable_message),
                                onRetry = { recreate() },
                                onExit = { finish() }
                            )
                        }

                        ConnectivityRepository.NetworkStatus.Unknown -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                AnimatedShimmer()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherApp(navController: NavHostController, weatherViewModel: WeatherViewModel) {

    // VERİLER
    weatherViewModel.fetchWeatherData("Izmir", "TR")
    weatherViewModel.exampleAirPollutionData()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        val tweenDuration = 750

        composable(
            route = Screen.Home.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(tweenDuration)
                )
            },
            popEnterTransition = {

                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(tweenDuration)
                )
            }
        ) {
            HomeScreen(navController = navController, viewModel = weatherViewModel)
        }

        composable(
            route = Screen.Details.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(tweenDuration)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(tweenDuration)
                )
            }
        ) {
            DetailsScreen(navController = navController, viewModel = weatherViewModel)
        }

        composable(
            route = Screen.Settings.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(tweenDuration)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(tweenDuration)
                )
            }
        ) {
            SettingsScreen(navController = navController)
        }
    }
}
