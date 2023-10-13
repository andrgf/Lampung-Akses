package com.example.newsapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.dto.Article

class SearchPagingSource(
    private val newsApi: NewsApi,
    private val query: String,
    private val source: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var newsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getSearchNews(query = query, sources = source, page = page)
            newsCount += newsResponse.articles.size
            val article = newsResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = article,
                nextKey = if (newsCount == newsResponse.totalResults) null else page +1,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }
}