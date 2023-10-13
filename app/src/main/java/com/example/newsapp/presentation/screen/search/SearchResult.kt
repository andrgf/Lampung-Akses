package com.example.newsapp.presentation.screen.search

import androidx.paging.PagingData
import com.example.newsapp.data.remote.dto.Article
import kotlinx.coroutines.flow.Flow

data class SearchResult(
    val query: String = "",
    val article: Flow<PagingData<Article>>? = null
)