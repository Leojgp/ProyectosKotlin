package com.leojgp.usingroooms.UserInterface.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leojgp.usingroooms.MainState
import com.leojgp.usingroooms.User
import com.leojgp.usingroooms.model.data.repositories.UserRepository
import kotlinx.coroutines.launch

class MainViewModel( private val repository:UserRepository): ViewModel(){
     var state by mutableStateOf(MainState())
    private set

    fun onNameChange(name:String){
        state = state.copy(
            name = name
        )
    }

    fun saveUser(){
        val user = User(
            state.name
        )
        viewModelScope.launch {
            repository.insertUsers(user)
        }
    }
}