package com.example.newsapp.presentation.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.screen.home.component.ArticleList

@Composable
fun SearchScreen(
    result: SearchResult,
    event: (SearchEvent) -> Unit,
    navigateToDetail:(Article)-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp, bottom = 4.dp)
            .statusBarsPadding()
    ) {
        SearchBar(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            text = result.query,
            readOnly = false,
            onSearchChange = { event(SearchEvent.UpdateQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        result.article?.let {
            val article = it.collectAsLazyPagingItems()
            ArticleList(
                articles = article,
                onClick = navigateToDetail
            )
        }
    }

}