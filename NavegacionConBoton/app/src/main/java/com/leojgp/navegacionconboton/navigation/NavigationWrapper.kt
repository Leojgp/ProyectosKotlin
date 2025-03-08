package com.leojgp.navegacionconboton.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.leojgp.navegacionconboton.HomeScreen
import com.leojgp.navegacionconboton.LoginScreen


@Composable
fun NavigationWrapper() {
    /*Es el objeto que gestiona todo en la navegación (ir, volver)*/
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen { numero -> navController.navigate(Home(numero = numero)) }
        }
        // BackStack contiene información sobre la pantalla actual, incluyendo sus argumentos de navegación.
        // Esto permite extraer los parámetros pasados a la pantalla Home.
        composable<Home> { backStackEntry ->
            val home: Home = backStackEntry.toRoute()
            HomeScreen(home.numero)
        }
    }
}