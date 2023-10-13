package com.example.newsapp.presentation.screen.search

sealed class SearchEvent {

    data class UpdateQuery(val query: String) : SearchEvent()

    object SearchNews : SearchEvent()
}
