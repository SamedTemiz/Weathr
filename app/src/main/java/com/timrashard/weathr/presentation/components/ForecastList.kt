package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.timrashard.weathr.data.model.Day
import com.timrashard.weathr.data.model.Hour
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.utils.DateTimeUtils

@Composable
fun HourlyForecastList(hours: List<Hour>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(hours) { data ->
            HourlyForecastItem(data)
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
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(110.dp, 150.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = hourlyData.datetime.substring(0, 5),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily,
                    color = Color.Gray
                )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_humidity),
                contentDescription = "Wind",
                modifier = Modifier.size(36.dp)
            )

            Text(
                text = "${hourlyData.temp.toInt()}°",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    }
}

@Composable
fun DailyForecastItem(dayData: Day, isFirst: Boolean = false) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(110.dp, 150.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = if(isFirst) DateTimeUtils.getLocalizedTodayName() else DateTimeUtils.formatDate(dayData.datetime, "dd MMMM"),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily,
                    color = Color.Gray
                )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_rain),
                contentDescription = "Wind",
                modifier = Modifier.size(36.dp)
            )

            Text(
                text = "${dayData.temp}°",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    }
}