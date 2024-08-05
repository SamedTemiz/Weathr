package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.domain.model.CurrentAirPollution
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily

@Composable
fun CurrentAirPollution(airData: CurrentAirPollution) {
    val pollutant = airData.components

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.very_poor),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f)
            )

            Text(
                text = "Very Poor",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = displayFontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            items(4) { index ->
                when (index) {
                    0 -> AirPollutionDetailsItem(
                        pollutant = stringResource(id = R.string.NO2),
                        value = pollutant.no2
                    )

                    1 -> AirPollutionDetailsItem(
                        pollutant = stringResource(id = R.string.PM10),
                        value = pollutant.pm10
                    )

                    2 -> AirPollutionDetailsItem(
                        pollutant = stringResource(id = R.string.O3),
                        value = pollutant.o3
                    )

                    3 -> AirPollutionDetailsItem(
                        pollutant = stringResource(id = R.string.PM2_5),
                        value = pollutant.pm2_5
                    )
                }
            }
        }
    }
}

@Composable
fun AirPollutionDetailsItem(pollutant: String, value: Double) {
    val pollutantIconResId = when (pollutant) {
        stringResource(id = R.string.PM10) -> R.drawable.exhaust_pipe       // NO2
        stringResource(id = R.string.NO2) -> R.drawable.dust                // PM10
        stringResource(id = R.string.O3) -> R.drawable.ozone                // O3
        stringResource(id = R.string.PM2_5) -> R.drawable.inhale            // PM2.5
        else -> R.drawable.no_data          // Unknown
    }

    val pollutantString = buildAnnotatedString {
        val parts = pollutant.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)|_".toRegex())
        append(parts[0])
        if (parts.size > 1) {
            withStyle(
                style = SpanStyle(
                    fontSize = 14.sp,
                    baselineShift = BaselineShift.Subscript
                )
            ) {
                parts.drop(1).forEach {
                    append(it.replace("_", "."))
                }
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = pollutantIconResId),
            contentDescription = "$pollutant Icon",
            modifier = Modifier.size(32.dp)
        )

        Text(
            text = pollutantString,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = bodyFontFamily,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )

        Text(
            text = "%.2f".format(value),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = displayFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}