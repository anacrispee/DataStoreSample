package com.example.datastoresample.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val USER_NAME = stringPreferencesKey("user_name")
    }

    val currentUserName: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[USER_NAME] ?: "Unknown"
        }

    suspend fun saveUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
        }
    }
}