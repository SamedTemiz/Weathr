package com.timrashard.weathr.presentation.weathr.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.presentation.components.AnimatedShimmer
import com.timrashard.weathr.presentation.components.SingleIconTopBar
import com.timrashard.weathr.presentation.components.WeatherDetailsCard
import com.timrashard.weathr.presentation.weathr.Screen
import com.timrashard.weathr.presentation.weathr.WeatherViewModel
import com.timrashard.weathr.presentation.weathr.home.sections.AirQualitySection
import com.timrashard.weathr.presentation.weathr.home.sections.ForecastSection
import com.timrashard.weathr.presentation.weathr.home.sections.OtherConditionsSection
import com.timrashard.weathr.presentation.weathr.home.sections.TemperatureSection
import com.timrashard.weathr.utils.DateTimeUtils

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: WeatherViewModel
) {
    val weatherState by viewModel.weatherData.collectAsState()

    val configuration = LocalConfiguration.current
    Log.d("HomeScreen", "Screen height: ${configuration.screenHeightDp}")

    Surface(
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column(
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

                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        TemperatureSection(
                            viewModel,
                            weatherData!!.currentConditions
                        ) // Image Height

                        WeatherDetailsCard(weatherData.currentConditions) // 110.dp

//                        ForecastSection(weatherData.days) // 200.dp

//                        AirQualitySection(viewModel) // 275.dp

                        OtherConditionsSection()
                    }
                }

                is Resource.Error -> {
                    Text("Error: ${weatherState.message}")
                }
            }
        }
    }
}