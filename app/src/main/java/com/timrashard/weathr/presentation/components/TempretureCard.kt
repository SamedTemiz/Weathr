package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.data.model.CurrentConditions
import com.timrashard.weathr.presentation.theme.AppTypography
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily

@Composable
fun TemperatureCard(
    currentConditions: CurrentConditions
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${currentConditions.temp.toInt()}°",
                style = TextStyle(
                    fontSize = 96.sp,
                    fontFamily = displayFontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
            Text(
                text = "${currentConditions.conditions} #",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily,
                    color = Color.Gray
                )
            )
        }
    }
}