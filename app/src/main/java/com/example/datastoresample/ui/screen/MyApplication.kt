package com.example.datastoresample.ui.screen

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastoresample.data.UserRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings"
)

class MyApplication : Application() {
    lateinit var userRepository: UserRepository
    override fun onCreate() {
        super.onCreate()
        userRepository = UserRepository(dataStore)
    }
}