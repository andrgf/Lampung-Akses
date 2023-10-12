package com.example.newsapp.domain.usecases.pref

import com.example.newsapp.domain.pref.UserPref
import kotlinx.coroutines.flow.Flow

class ReadPref(
    private val userPref: UserPref
) {
    operator fun invoke(): Flow<Boolean> {
        return userPref.readPrefKey()
    }
}