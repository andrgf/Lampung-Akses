package com.example.newsapp.presentation.screen.home.tabs

import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.colorResource
import com.example.newsapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import java.lang.reflect.Modifier

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabsItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
        contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = androidx.compose.ui.Modifier.pagerTabIndicatorOffset(
                    pagerState,
                    tabPositions
                )
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            Tab(
                selected = pagerState.currentPage == index,
                text = { Text(text = tab.title)},
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}