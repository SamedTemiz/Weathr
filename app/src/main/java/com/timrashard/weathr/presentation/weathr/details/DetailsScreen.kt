package com.timrashard.weathr.presentation.weathr.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.presentation.components.DoubleIconTopBar
import com.timrashard.weathr.presentation.components.TemperatureCard
import com.timrashard.weathr.presentation.components.WeatherDetailsCard
import com.timrashard.weathr.presentation.components.WeeklyForecastList
import com.timrashard.weathr.presentation.weathr.Screen
import com.timrashard.weathr.presentation.weathr.WeatherViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: WeatherViewModel
) {
    val weatherState by viewModel.weatherData.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            when (weatherState) {
                is Resource.Loading -> {
                    Text("Loading...")
                }

                is Resource.Success -> {
                    val weatherData = weatherState.data

                    DoubleIconTopBar(
                        title = weatherData?.resolvedAddress ?: "Searching...",
                        onBackClick = {
                            navController.navigateUp()
                        },
                        onSettingsClick = {
                            navController.popBackStack()
                            navController.navigate(Screen.Settings.route)
                        }
                    )

                    TemperatureCard(currentConditions = weatherData!!.currentConditions)

                    WeatherDetailsCard(currentConditions = weatherData.currentConditions)

                    WeeklyForecastList(weatherData.days.subList(0, 7))
                }

                is Resource.Error -> {
                    Text("Error: ${weatherState.message}")
                }
            }
        }
    }
}