package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.timrashard.weathr.common.Constant.ASSETS_BASE_URL
import com.timrashard.weathr.data.model.Day
import com.timrashard.weathr.data.model.Hour
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.utils.DateTimeUtils
import java.time.LocalTime

@Composable
fun HourlyForecastList(hours: List<Hour>, isToday: Boolean = false) {
    val currentTime = LocalTime.now()
    val startHour = if (isToday) {
        hours.indexOfFirst {
            it.datetime.substring(0, 2).toInt() >= currentTime.hour
        }
    } else {
        0
    }

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(hours.drop(startHour)) { hourlyData ->
            HourlyForecastItem(hourlyData)
        }
    }
}

@Composable
fun DailyForecastList(days: List<Day>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(days) { index, data ->
            DailyForecastItem(data, isFirst = index == 0)
        }
    }
}

@Composable
fun HourlyForecastItem(hourlyData: Hour) {
    ForecastItem(
        datetime = hourlyData.datetime,
        temp = "${hourlyData.temp.toInt()}°",
        isHourly = true,
        iconName = hourlyData.icon
    )
}

@Composable
fun DailyForecastItem(dayData: Day, isFirst: Boolean = false) {
    ForecastItem(
        datetime = dayData.datetime,
        temp = "${dayData.temp.toInt()}°",
        isHourly = false,
        isFirst = isFirst,
        iconName = dayData.icon
    )
}

@Composable
fun ForecastItem(
    datetime: String,
    temp: String,
    isHourly: Boolean,
    isFirst: Boolean = false,
    iconName: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(0.75f)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(vertical = 20.dp)
        ) {
            Text(
                text = if (isHourly) datetime.substring(0, 5)
                else if (isFirst) DateTimeUtils.getLocalizedTodayName()
                else DateTimeUtils.formatDate(
                    datetime,
                    "dd MMMM"
                ),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily,
                    color = Color.Gray
                )
            )

            Spacer(Modifier.height(8.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$ASSETS_BASE_URL$iconName.png")
                    .crossfade(true)
                    .build(),
                contentDescription = "Icon",
                modifier = Modifier.size(48.dp)
            )

            Spacer(Modifier.height(8.dp))
            Text(
                text = temp,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = bodyFontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    }
}