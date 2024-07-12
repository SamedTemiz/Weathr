package com.timrashard.weathr.presentation.weathr.main.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.AppTypography
import com.timrashard.weathr.presentation.theme.WeathrTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastSection() {
    val tabs = listOf("Today", "Tomorrow", "Next 10 days")
    val pagerState = rememberPagerState(pageCount = {tabs.size})

    Column {
        ForecastTabsSection(tabList = tabs, pagerState = pagerState)
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> ForecastList()
                1 -> ForecastList()
                2 -> ForecastList()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastTabsSection(
    tabList: List<String>,
    pagerState: PagerState
){
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        tabList.forEachIndexed { index, tab ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(end = 40.dp)
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
            ) {
                Text(
                    text = tab,
                    color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.tertiary else Color.LightGray,
                    style = if (pagerState.currentPage == index) TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = AppTypography.bodyLarge.fontFamily
                    ) else TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontFamily = AppTypography.bodyLarge.fontFamily
                    ),
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (pagerState.currentPage == index) {
                    Box(
                        modifier = Modifier
                            .height(4.dp)
                            .width(4.dp)
                            .background(Color.White, shape = CircleShape)
                    )
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun ForecastList(){
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(5) {
            ForecastItem(
                time = "11 am",
                icon = R.drawable.humidity,
                temperature = "18°"
            )
        }
    }
}

@Composable
fun ForecastItem(time: String, icon: Int, temperature: String) {
    val valueFont = AppTypography.bodyLarge.copy(fontSize = 20.sp)
    val timeFont = AppTypography.bodyMedium

    Box(
        modifier = Modifier
            .background(color = Color(0xFF202329), shape = RoundedCornerShape(15.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 20.dp)
        ) {
            Text(
                text = time,
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = timeFont.fontSize
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = icon),
                contentDescription = time,
                modifier = Modifier.size(36.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = temperature,
                style = TextStyle(
                    color = Color.White,
                    fontSize = valueFont.fontSize
                )
            )
        }
    }
}

@Preview
@Composable
fun HourlyForecastSectionPreview() {
    WeathrTheme {
        ForecastSection()
    }
}