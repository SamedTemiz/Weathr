package com.timrashard.weathr.presentation.weathr.main.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.AppTypography
import com.timrashard.weathr.presentation.theme.WeathrTheme

@Composable
fun WeatherDetailsSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF202329), shape = RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .background(color = Color(0xFF202329), shape = RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeatherDetailItem(
                icon = R.drawable.wind,
                value = "10 m/s",
                description = "Wind"
            )
            WeatherDetailItem(
                icon = R.drawable.humidity,
                value = "98%",
                description = "Humidity"
            )
            WeatherDetailItem(
                icon = R.drawable.rain,
                value = "100%",
                description = "Rain"
            )
        }
    }
}

@Composable
fun WeatherDetailItem(icon: Int, value: String, description: String) {
    val valueFont = AppTypography.bodyLarge.copy(fontSize = 20.sp)
    val categoryFont = AppTypography.bodyMedium

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = description,
            modifier = Modifier.size(30.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            style = TextStyle(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = valueFont.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = valueFont.fontFamily
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            style = TextStyle(
                color = Color.LightGray,
                fontSize = categoryFont.fontSize,
                fontFamily = categoryFont.fontFamily
            )
        )
    }
}

@Preview
@Composable
fun WeatherDetailsSectionPreview(){
    WeathrTheme {
        WeatherDetailsSection()
    }
}