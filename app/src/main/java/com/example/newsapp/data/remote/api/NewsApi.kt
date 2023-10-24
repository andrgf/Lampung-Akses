package com.example.newsapp.data.remote.api

import com.example.newsapp.data.remote.dto.NewsResponse
import com.example.newsapp.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNewsEverything(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ) : NewsResponse

    @GET("everything")
    suspend fun getSearchNews(
        @Query("q") query: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ) : NewsResponse

}