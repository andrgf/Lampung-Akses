package com.example.newsapp.di

import android.app.Application
import com.example.newsapp.data.pref.UserPrefImpl
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.pref.UserPref
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecases.news.GetNews
import com.example.newsapp.domain.usecases.news.NewsUseCases
import com.example.newsapp.domain.usecases.pref.AppPrefUseCases
import com.example.newsapp.domain.usecases.pref.ReadPref
import com.example.newsapp.domain.usecases.pref.SavePref
import com.example.newsapp.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // preferences datastore
    @Provides
    @Singleton
    fun provideUserPref(
        application: Application
    ): UserPref = UserPrefImpl(application)

    @Provides
    @Singleton
    fun provideAppPrefUseCases(
        userPref: UserPref
    ) = AppPrefUseCases(
        readPref = ReadPref(userPref),
        savePref = SavePref(userPref)
    )

    // getNews
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }
}