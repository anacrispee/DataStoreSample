package com.example.datastoresample.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.datastoresample.data.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class UiState(
    val userName: String
)

class MyAppViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    val uiState: StateFlow<UiState> =
        userRepository.currentUserName.map { userName ->
            UiState(userName)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState("Unknown")
        )

    fun saveUserName(
        userName: String
    ) {
        viewModelScope.launch {
            userRepository.saveUserName(userName)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                MyAppViewModel(application.userRepository)
            }
        }
    }
}