package com.leojgp.navegacionconboton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(navigateToHome:(Int) -> Unit){

    var number by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "LOGIN SCREEN", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {number++}) {
            Text("Pulse")
        }
        Text("$number")
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToHome(number) }){
            Text(text= "Navegar a la home")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
