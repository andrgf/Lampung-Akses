package com.example.newsapp.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.presentation.screen.home.component.ArticleList
import com.example.newsapp.presentation.screen.home.component.HomeTopBar

@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    navigateToDetail:(Article)-> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 4.dp, bottom = 4.dp)
        .statusBarsPadding()
    ) {
        HomeTopBar()

        ArticleList(
            articles = article,
            onClick = navigateToDetail
        )
    }
}