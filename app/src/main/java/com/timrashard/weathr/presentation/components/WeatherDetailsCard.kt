package com.timrashard.weathr.presentation.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.data.model.CurrentConditions
import com.timrashard.weathr.presentation.theme.WeathrTheme
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.weathr.WeatherViewModel

@Composable
fun WeatherDetailsCard(
    currentConditions: CurrentConditions?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            WeatherDetailItem(
                icon = R.drawable.wind,
                value = "${currentConditions!!.windspeed.toInt()} m/s",
                description = "Wind"
            )
            WeatherDetailItem(
                icon = R.drawable.humidity,
                value = "${currentConditions.humidity.toInt()}%",
                description = "Humidity"
            )
            WeatherDetailItem(
                icon = R.drawable.rainprep,
                value = "${currentConditions.precipprob.toInt()}%",
                description = "Rain"
            )
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
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Component",
            modifier = Modifier.size(32.dp)
        )

        Spacer(Modifier.height(2.dp))
        Text(
            text = value,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = bodyFontFamily,
                color = MaterialTheme.colorScheme.tertiary
            )
        )

        Spacer(Modifier.height(2.dp))
        Text(
            text = description,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = bodyFontFamily,
                color = Color.Gray
            )
        )
    }
}