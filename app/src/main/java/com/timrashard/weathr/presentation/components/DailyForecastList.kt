package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.WeathrTheme

@Composable
fun DailyForecastList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(7) { data ->
            DayItem()
        }
    }
}

@Composable
fun DayItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Wednesday",
            style = TextStyle(
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "15°",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth(0.5f)
                    .background(color = Color.Black, shape = RoundedCornerShape(2.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(((21 - 15) * 10).dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF80ED99), Color(0xFF00FFC6), Color(0xFF30AADD))
                            ),
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "21°",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Image(
            painter = painterResource(id = R.drawable.rain),
            contentDescription = null,
            modifier = Modifier.size(36.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
fun DaylyForecastListPreview() {
    WeathrTheme {
        DailyForecastList()
    }
}

@Preview
@Composable
fun DayItemPreview() {
    WeathrTheme {
        DayItem()
    }
}