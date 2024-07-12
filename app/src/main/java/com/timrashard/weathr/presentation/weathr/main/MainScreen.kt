package com.timrashard.weathr.presentation.weathr.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timrashard.weathr.presentation.theme.WeathrTheme
import com.timrashard.weathr.presentation.weathr.main.sections.ForecastSection
import com.timrashard.weathr.presentation.weathr.main.sections.LocationAndDateSection
import com.timrashard.weathr.presentation.weathr.main.sections.TemperatureSection
import com.timrashard.weathr.presentation.weathr.main.sections.WeatherDetailsSection
import com.timrashard.weathr.presentation.weathr.main.sections.WeatherMapSection

@Composable
fun MainScreen() {
    Surface(
        color = MaterialTheme.colorScheme.secondary
    ){
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LocationAndDateSection()

            TemperatureSection()

            WeatherDetailsSection()

            ForecastSection()

            WeatherMapSection()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    WeathrTheme {
        MainScreen()
    }
}