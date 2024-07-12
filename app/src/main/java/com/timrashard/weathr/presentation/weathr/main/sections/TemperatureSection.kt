package com.timrashard.weathr.presentation.weathr.main.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.AppTypography
import com.timrashard.weathr.presentation.theme.WeathrTheme

@Composable
fun TemperatureSection() {
    val degreeStyle = AppTypography.displayLarge
    val weatherStyle = AppTypography.bodyLarge

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Text(
                text = "18°",
                style = TextStyle(
                    fontSize = 100.sp,
                    fontFamily = degreeStyle.fontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
            Text(
                text = "Thunderstorm",
                style = TextStyle(
                    fontSize = weatherStyle.fontSize,
                    fontFamily = weatherStyle.fontFamily,
                    color = Color.Gray
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ornek),
            contentDescription = "Sun",
            modifier = Modifier.size(150.dp)
        )
    }
}

@Preview
@Composable
fun WeatherDataSectionPreview(){
    WeathrTheme {
        TemperatureSection()
    }
}