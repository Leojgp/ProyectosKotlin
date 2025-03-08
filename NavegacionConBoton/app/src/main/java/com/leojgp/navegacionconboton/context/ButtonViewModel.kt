package com.leojgp.navegacionconboton.context

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random


class ButtonViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow<Int>(0)
    val uiState: StateFlow<Int> = _uiState

    // Handle business logic
  fun incrementaCount(){
        _uiState.value += 1
    }
}