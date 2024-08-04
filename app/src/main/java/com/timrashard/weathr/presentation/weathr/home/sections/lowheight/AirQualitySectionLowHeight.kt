package com.timrashard.weathr.presentation.weathr.home.sections.lowheight

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.model.air.toCurrentAirPollution
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily
import com.timrashard.weathr.presentation.weathr.WeatherViewModel

@Composable
fun AirQualitySectionLowHeight(viewModel: WeatherViewModel) {
    val airState by viewModel.airData.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AirPollutionHeader()

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(8.dp) // Yüksekliği ayarlamak için padding ekleyebilirsiniz
        ) {
            when (airState) {
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    val pollutionData = airState.data?.toCurrentAirPollution()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.poor),
                            contentDescription = "",
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                        )

                        val pollutant = pollutionData?.components

                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            items(4) { index ->
                                when (index) {
                                    0 -> AirPollutionDetailsItemLowHeight(
                                        pollutant = stringResource(id = R.string.NO2),
                                        value = pollutant!!.no2
                                    )
                                    1 -> AirPollutionDetailsItemLowHeight(
                                        pollutant = stringResource(id = R.string.PM10),
                                        value = pollutant!!.pm10
                                    )
                                    2 -> AirPollutionDetailsItemLowHeight(
                                        pollutant = stringResource(id = R.string.O3),
                                        value = pollutant!!.o3
                                    )
                                    3 -> AirPollutionDetailsItemLowHeight(
                                        pollutant = stringResource(id = R.string.PM2_5),
                                        value = pollutant!!.pm2_5
                                    )
                                }
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    Text("Error: ${airState.message}")
                }
            }
        }
    }
}

@Composable
fun AirPollutionHeader() {
    val iconSize = 36.dp

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Air Pollution",
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Image(
                painter = painterResource(id = R.drawable.good),
                contentDescription = "",
                modifier = Modifier.size(iconSize)
            )

            Image(
                painter = painterResource(id = R.drawable.fair),
                contentDescription = "",
                modifier = Modifier.size(iconSize)
            )

            Image(
                painter = painterResource(id = R.drawable.moderate),
                contentDescription = "",
                modifier = Modifier.size(iconSize)
            )

            Image(
                painter = painterResource(id = R.drawable.poor),
                contentDescription = "",
                modifier = Modifier.size(iconSize)
            )

            Image(
                painter = painterResource(id = R.drawable.very_poor),
                contentDescription = "",
                modifier = Modifier.size(iconSize)
            )
        }
    }
}

@Composable
fun AirPollutionDetailsItemLowHeight(pollutant: String, value: Double) {
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
                    fontSize = 12.sp,
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
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = pollutantString,
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = bodyFontFamily,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )

        Text(
            text = "%.2f".format(value),
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = displayFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}