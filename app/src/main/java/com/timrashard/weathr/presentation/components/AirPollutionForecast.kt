package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.domain.model.HourlyForecastAirPollution
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily
import com.timrashard.weathr.utils.DateTimeUtils

@Composable
fun AirPollutionForecast(
    airData: HourlyForecastAirPollution,
) {
    val airDataList = airData.list

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Last 24 hours",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 18.sp
            )
            Spacer(Modifier.width(8.dp))
            AirPollutionEmojiScale(modifier = Modifier.weight(1f))
        }

        Spacer(Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier

        ) {
            items(airDataList.size) { index ->
                val air = airDataList[index]
                var imageResId = 0
                var aqiType = "no-data"

                when (air.main.aqi) {
                    1 -> {
                        imageResId = R.drawable.good
                        aqiType = "Good"
                    }

                    2 -> {
                        imageResId = R.drawable.fair
                        aqiType = "Fair"
                    }

                    3 -> {
                        imageResId = R.drawable.moderate
                        aqiType = "Moderate"
                    }

                    4 -> {
                        imageResId = R.drawable.poor
                        aqiType = "Poor"
                    }

                    5 -> {
                        imageResId = R.drawable.very_poor
                        aqiType = "Very Poor"
                    }

                    else -> R.drawable.no_data
                }

                AirPollutionForecastItem(
                    hour = DateTimeUtils.convertEpochToLocalTime(air.dt),
                    iconResId = imageResId,
                    type = aqiType,
                )
            }
        }
    }
}

@Composable
fun AirPollutionForecastItem(
    hour: String,
    iconResId: Int,
    type: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxHeight()
            .width(75.dp)
    ) {
        Text(
            text = hour,
            fontFamily = displayFontFamily,
            color = Color.White,
            fontSize = 18.sp
        )

        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Air Quality",
            modifier = Modifier.aspectRatio(1f)
        )
        Text(
            text = type,
            fontFamily = bodyFontFamily,
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AirPollutionEmojiScale(
    modifier: Modifier
){
    val emojiList = listOf(
        R.drawable.good,
        R.drawable.fair,
        R.drawable.moderate,
        R.drawable.poor,
        R.drawable.very_poor,
    )

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier
    ){
        emojiList.forEach{ emoji ->
            Spacer(Modifier.width(8.dp))
            Image(
                painter = painterResource(id = emoji),
                contentDescription = "Emoji",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}
