package com.example.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews (
    private val newsRepository: NewsRepository
) {
    operator fun invoke(query: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getSearch(query = query, sources = sources)
    }
}