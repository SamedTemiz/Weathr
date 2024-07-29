package com.timrashard.weathr.presentation.weathr.home.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.timrashard.weathr.R
import com.timrashard.weathr.data.model.Day
import com.timrashard.weathr.presentation.components.DailyForecastList
import com.timrashard.weathr.presentation.components.HourlyForecastList
import com.timrashard.weathr.presentation.components.Tabs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastSection(days: List<Day>) {
    val tabs = listOf(
        stringResource(id = R.string.today),
        stringResource(id = R.string.tomorrow),
        stringResource(id = R.string.next_14_days),
    )
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Tabs(tabs, pagerState, coroutineScope)

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> HourlyForecastList(days.first().hours, isToday = true) // Today
                1 -> HourlyForecastList(days[1].hours, isToday = false)     // Tomorrow
                2 -> DailyForecastList(days)                                // Next 10 days
            }
        }
    }
}