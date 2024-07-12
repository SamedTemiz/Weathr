package com.timrashard.weathr.presentation.weathr.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.timrashard.weathr.presentation.components.DailyForecastList
import com.timrashard.weathr.presentation.components.DoubleIconTopBar
import com.timrashard.weathr.presentation.components.WeatherDetailsCard
import com.timrashard.weathr.presentation.theme.AppTypography

@Composable
fun DetailsScreen(navController: NavController) {
    Surface(
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DoubleIconTopBar(
                title = "Stuttgart",
                onBackClick = {
                    navController.navigateUp()
                },
                onSettingsClick = {

                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            TemperatureCard()

            Spacer(modifier = Modifier.height(16.dp))
            WeatherDetailsCard(currentConditions = null)

            Spacer(modifier = Modifier.height(16.dp))
            DailyForecastList()
        }
    }
}

@Composable
fun TemperatureCard() {
    val weatherStyle = AppTypography.bodyLarge

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "20°",
                style = TextStyle(
                    fontSize = 80.sp,
                    fontFamily = AppTypography.displayLarge.fontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
            Text(
                text = "Thunderstorm #",
                style = TextStyle(
                    fontSize = weatherStyle.fontSize,
                    fontFamily = weatherStyle.fontFamily,
                    color = Color.Gray
                )
            )
        }
    }
}