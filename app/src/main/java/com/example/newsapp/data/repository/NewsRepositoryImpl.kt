package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.data.remote.paging.NewsPagingSource
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsApi: NewsApi): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 8),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi, source = sources.joinToString(separator = ","))
            }
        ).flow
    }
}