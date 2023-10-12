package com.example.newsapp.domain.pref

import kotlinx.coroutines.flow.Flow

interface UserPref {

    suspend fun savePrefKey()

    fun readPrefKey(): Flow<Boolean>
}