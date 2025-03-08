package com.leojgp.usingroooms.UserInterface.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leojgp.usingroooms.UserInterface.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp)) // Corrección del Spacer
        TextField(
            value = state.name, // Asegurar que `state` es accesible
            onValueChange = { viewModel.onNameChange(it) },
            label = { Text("Nombre") }
        )
        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos
        Button(onClick = { viewModel.saveUser() }) {
            Text("Guardar")
        }
    }
}
