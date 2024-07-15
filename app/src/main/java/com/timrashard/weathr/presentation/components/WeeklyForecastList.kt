package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.data.model.Day
import com.timrashard.weathr.utils.DateTimeUtils

@Composable
fun WeeklyForecastList(dayList: List<Day>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(dayList) { index, data ->
            DayItem(data, isFirst = index == 0)
        }
    }
}

@Composable
fun DayItem(day: Day, isFirst: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = if(isFirst) DateTimeUtils.getLocalizedTodayName() else DateTimeUtils.getDayName(day.datetime),
            style = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.weight(1f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = "${day.tempmin.toInt()}°",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            )

            val tempRange = day.tempmax - day.tempmin
            val barWidth = (tempRange * 5).dp
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth(0.5f)
                    .background(color = Color.Black, shape = RoundedCornerShape(2.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.Center)
                        .width(barWidth)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF80ED99), Color(0xFF00FFC6), Color(0xFF30AADD))
                            ),
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }

            Text(
                text = "${day.tempmax.toInt()}°",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.sunny_day),
            contentDescription = null,
            modifier = Modifier.size(34.dp),
            contentScale = ContentScale.Fit
        )
    }
}