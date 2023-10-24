package com.example.newsapp.presentation.screen.home.content

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.presentation.screen.home.component.ArticleList

@Composable
fun EverythingScreen(
    article: LazyPagingItems<Article>,
    navigateToDetail: (Article) -> Unit
) {
    ArticleList(articles = article, onClick = navigateToDetail)
}