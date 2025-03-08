package com.leojgp.navegacionconboton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.leojgp.navegacionconboton.context.ButtonViewModel
import com.leojgp.navegacionconboton.navigation.NavigationWrapper
import com.leojgp.navegacionconboton.ui.theme.NavegacionConBotonTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacionConBotonTheme {
                NavigationWrapper()
            }
        }
    }
}

