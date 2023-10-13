package com.example.newsapp.presentation.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    private var _result = mutableStateOf(SearchResult())
    val result: State<SearchResult> = _result

    fun event(searchEvent: SearchEvent) {
        when(searchEvent) {
            is SearchEvent.UpdateQuery -> {
                _result.value = _result.value.copy(query = searchEvent.query)
            }
            is SearchEvent.SearchNews -> {
                getSearch()
            }
        }
    }

    private fun getSearch() {
        val article = newsUseCases.search(
            query = _result.value.query,
            sources = listOf("bbc-news", "abc-news", "detik.com")
        ).cachedIn(viewModelScope)
        _result.value = _result.value.copy(article = article)
    }
}