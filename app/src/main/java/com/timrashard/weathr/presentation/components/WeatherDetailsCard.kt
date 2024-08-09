package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.data.model.CurrentConditions
import com.timrashard.weathr.presentation.theme.bodyFontFamily

@Composable
fun WeatherDetailsCard(
    currentConditions: CurrentConditions?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        currentConditions?.let { data ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                WeatherDetailItem(
                    icon = R.drawable.wind,
                    value = "${data.windspeed.toInt()} m/s",
                    description = "Wind"
                )
                WeatherDetailItem(
                    icon = R.drawable.humidity,
                    value = "${data.humidity.toInt()}%",
                    description = "Humidity"
                )
                WeatherDetailItem(
                    icon = R.drawable.rainprep,
                    value = "${data.precipprob.toInt()}%",
                    description = "Rain"
                )
            }
        } ?: run {
            CircularProgressComponent()
        }
    }
}

@Composable
fun WeatherDetailItem(
    icon: Int,
    value: String,
    description: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Component",
            modifier = Modifier.size(48.dp)
        )

        Text(
            text = value,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = bodyFontFamily,
                color = MaterialTheme.colorScheme.tertiary
            )
        )

        Text(
            text = description,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = bodyFontFamily,
                color = Color.Gray
            )
        )
    }
}