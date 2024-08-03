package com.timrashard.weathr.presentation.weathr.home.sections

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.model.air.AirQualityIndex
import com.timrashard.weathr.data.model.air.toCurrentAirPollution
import com.timrashard.weathr.data.model.air.toHourlyForecastAirPollution
import com.timrashard.weathr.domain.model.CurrentAirPollution
import com.timrashard.weathr.domain.model.HourlyForecastAirPollution
import com.timrashard.weathr.presentation.components.Tabs
import com.timrashard.weathr.presentation.theme.WeathrTheme
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily
import com.timrashard.weathr.presentation.weathr.WeatherViewModel
import com.timrashard.weathr.utils.DateTimeUtils

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AirQualitySection(viewModel: WeatherViewModel) {
    val airState by viewModel.airData.collectAsState()

    val tabs = listOf("Current Air Pollution", "Hourly Forecast")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Tabs(tabs, pagerState, coroutineScope)

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {

            when (airState) {
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    val airData = airState.data

                    airData?.let { data ->
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxWidth()
                        ) { page ->
                            when (page) {
                                0 -> CurrentAirPollution(data.toCurrentAirPollution())
                                1 -> TodayAirPollution(data.toHourlyForecastAirPollution())
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
fun CurrentAirPollution(airData: CurrentAirPollution) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.poor),
            contentDescription = "",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp))
                .aspectRatio(1f)
        )

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            val pollutant = airData.components

            AirPollutionDetailsItem(
                pollutant = stringResource(id = R.string.NO2),
                value = pollutant.no2
            )
            AirPollutionDetailsItem(
                pollutant = stringResource(id = R.string.PM10),
                value = pollutant.pm10,
            )

            AirPollutionDetailsItem(
                pollutant = stringResource(id = R.string.O3),
                value = pollutant.o3,
            )


            AirPollutionDetailsItem(
                pollutant = stringResource(id = R.string.PM2_5),
                value = pollutant.pm2_5,
            )
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
            modifier = Modifier.size(24.dp)
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
            fontSize = 18.sp,
            fontFamily = displayFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun TodayAirPollution(
    airData: HourlyForecastAirPollution,
) {
    val airDataList = airData.list

    val localDensity = LocalDensity.current
    var boxHeight by remember { mutableStateOf(0.dp) }
    val itemHeight = 75.dp

    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
            .onGloballyPositioned { coordinates ->
                boxHeight = with(localDensity) { coordinates.size.height.toDp() }
                Log.d("DİĞER", "$boxHeight")
            }
    ) {
        items(airDataList.size) { index ->
            val air = airDataList[index]
            val imageResId = when (air.main.aqi) {
                1 -> R.drawable.good // En iyi değer
                2 -> R.drawable.fair
                3 -> R.drawable.moderate
                4 -> R.drawable.poor
                5 -> R.drawable.very_poor // En kötü değer
                else -> R.drawable.no_data
            }

            val sectionHeight = (boxHeight - itemHeight) / 4
            val offsetY = when (air.main.aqi) {
                1 -> 0.dp // En iyi değer için üst konum
                2 -> sectionHeight * 1
                3 -> sectionHeight * 2
                4 -> sectionHeight * 3
                5 -> sectionHeight * 4 // En kötü değer için alt konum
                else -> 0.dp
            }

            Log.d("DİĞER", "$offsetY")

            AirPollutionForecastItem(
                iconResId = imageResId,
                hour = DateTimeUtils.convertEpochToLocalTime(air.dt),
                itemHeight = 75.dp,
                offSetY = offsetY
            )
        }
    }
}

@Composable
fun AirPollutionForecastItem(
    iconResId: Int,
    hour: String,
    itemHeight: Dp,
    offSetY: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(itemHeight)
            .offset(y = offSetY)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Air Quality",
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        )
        Text(
            text = hour,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

// UNDER 600dp

@Composable
fun AirQualitySectionLowHeight(viewModel: WeatherViewModel) {
    val airState by viewModel.airData.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Air Pollution",
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(4.dp) // Yüksekliği ayarlamak için padding ekleyebilirsiniz
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
                                .fillMaxWidth(0.4f)
                                .aspectRatio(1f)
                        )

                        val pollutant = pollutionData?.components

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp), // Row içindeki boşlukları ayarlayın
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
            modifier = Modifier.size(24.dp)
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
            fontSize = 18.sp,
            fontFamily = displayFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}
