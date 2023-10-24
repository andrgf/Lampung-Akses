package com.example.newsapp.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.presentation.screen.home.component.ArticleList
import com.example.newsapp.presentation.screen.home.component.HomeTopBar
import com.example.newsapp.presentation.screen.home.tabs.TabsItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    navigateToDetail: (Article) -> Unit
) {
    val tabs = listOf(
        TabsItem.Everything,
        TabsItem.Viral
    )
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp, bottom = 4.dp)
            .statusBarsPadding()
    ) {
        HomeTopBar()
        
        ArticleList(articles = article, onClick = navigateToDetail)
    }
}