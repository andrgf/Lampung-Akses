package com.example.newsapp.domain.usecases.pref

import com.example.newsapp.domain.pref.UserPref

class SavePref(
    private val userPref: UserPref
) {
    suspend operator fun invoke() {
        userPref.savePrefKey()
    }
}