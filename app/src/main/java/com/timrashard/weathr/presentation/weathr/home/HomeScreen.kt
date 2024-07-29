package com.timrashard.weathr.presentation.weathr.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.presentation.components.AnimatedShimmer
import com.timrashard.weathr.presentation.components.SingleIconTopBar
import com.timrashard.weathr.presentation.components.WeatherDetailsCard
import com.timrashard.weathr.presentation.theme.WeathrTheme
import com.timrashard.weathr.presentation.weathr.Screen
import com.timrashard.weathr.presentation.weathr.WeatherViewModel
import com.timrashard.weathr.presentation.weathr.home.sections.AirQualitySection
import com.timrashard.weathr.presentation.weathr.home.sections.ForecastSection
import com.timrashard.weathr.presentation.weathr.home.sections.TemperatureSection
import com.timrashard.weathr.utils.DateTimeUtils

@Composable
fun HomeScreen(
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
                .padding(16.dp)
        ) {
            when (weatherState) {
                is Resource.Loading -> {
                    AnimatedShimmer()
                }

                is Resource.Success -> {
                    val weatherData = weatherState.data

                    SingleIconTopBar(
                        title = weatherData?.resolvedAddress?.split(",")?.get(0) ?: "Searching...",
                        subtitle = DateTimeUtils.formatDateWithDayName(
                            weatherData?.days?.first()?.datetime ?: ""
                        ),
                        onMenuClick = {
                            navController.navigate(Screen.Details.route)
                        }
                    )

                    TemperatureSection(viewModel, weatherData!!.currentConditions)

                    WeatherDetailsCard(weatherData.currentConditions)

                    ForecastSection(weatherData.days)

                    AirQualitySection(viewModel)
                }

                is Resource.Error -> {
                    Text("Error: ${weatherState.message}")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    WeathrTheme {
        HomeScreen(navController = rememberNavController(), viewModel = hiltViewModel())
    }
}