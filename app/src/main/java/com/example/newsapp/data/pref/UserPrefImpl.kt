package com.example.newsapp.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.domain.pref.UserPref
import com.example.newsapp.util.Constant
import com.example.newsapp.util.Constant.USER_PREF
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPrefImpl(
    private val context: Context
): UserPref {
    override suspend fun savePrefKey() {
        context.dataStore.edit {
            it[PreferencesKey.APP_PREF] = true
        }
    }

    override fun readPrefKey(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PreferencesKey.APP_PREF] ?: false
        }
    }

}

private val readPreferences = preferencesDataStore(name = USER_PREF)

val Context.dataStore: DataStore<Preferences> by readPreferences

private object PreferencesKey {
    val APP_PREF = booleanPreferencesKey(name = Constant.APP_PREF)
}