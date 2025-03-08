package com.leojgp.usingroooms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.leojgp.usingroooms.UserInterface.view.MainScreen
import com.leojgp.usingroooms.UserInterface.viewmodel.MainViewModel
import com.leojgp.usingroooms.model.data.UserDatabase
import com.leojgp.usingroooms.model.data.repositories.UserRepository
import com.leojgp.usingroooms.ui.theme.UsingRooomsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(this,UserDatabase::class.java,"user_db").build()
        val dao = db.dao
        val repository = UserRepository(dao)
        val viewModel = MainViewModel(repository)
        enableEdgeToEdge()
        setContent {
            MainScreen(viewModel)
            }
        }
    }