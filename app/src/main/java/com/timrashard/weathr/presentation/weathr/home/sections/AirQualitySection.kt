package com.timrashard.weathr.presentation.weathr.home.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.presentation.components.DailyForecastList
import com.timrashard.weathr.presentation.components.HourlyForecastList
import com.timrashard.weathr.presentation.components.Tabs
import com.timrashard.weathr.presentation.weathr.WeatherViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AirQualitySection(viewModel: WeatherViewModel) {
    val airState by viewModel.airData.collectAsState()

    val tabs = listOf(
        "Today",
        "Last Week"
    )
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Tabs(tabs, pagerState, coroutineScope)

//        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> {}
                1 -> {}
            }
        }
    }

    var boxHeight by remember { mutableIntStateOf(0) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(15.dp))
            .onGloballyPositioned { coordinates ->
                boxHeight = coordinates.size.height
            }
    ) {
        when (airState) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }

            is Resource.Success -> {
                val airData = airState.data
                LazyRow(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    airData?.list?.let { airQualityData ->

                        items(airQualityData.size) { index ->
                            val dataPoint = airQualityData[index]
                            val imageResId = when (dataPoint.main.aqi) {
                                1 -> R.drawable.good // En iyi değer
                                2 -> R.drawable.fair
                                3 -> R.drawable.moderate
                                4 -> R.drawable.poor
                                5 -> R.drawable.very_poor // En kötü değer
                                else -> R.drawable.no_data
                            }

                            val offsetY = when (dataPoint.main.aqi) {
                                1 -> 0.1f * boxHeight // En iyi değer için üst konum
                                2 -> 0.3f * boxHeight
                                3 -> 0.5f * boxHeight
                                4 -> 0.7f * boxHeight
                                5 -> 0.9f * boxHeight // En kötü değer için alt konum
                                else -> 0.0f * boxHeight
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(horizontal = 8.dp)
                                    .background(Color.DarkGray)
                                    .offset(y = with(LocalDensity.current) { offsetY.toDp() })
                            ) {
                                Image(
                                    painter = painterResource(id = imageResId),
                                    contentDescription = "Air Quality",
                                    modifier = Modifier.size(75.dp)
                                )
                                Text(
                                    text = "12:00",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White,
                                    fontSize = 12.sp
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