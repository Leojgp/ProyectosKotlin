package com.leojgp.navigationguide.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.leojgp.navigationguide.DetailScreen
import com.leojgp.navigationguide.LoginScreen
import com.leojgp.navigationguide.HomeScreen

@Composable
fun NavigationWrapper(){
    //Es el objeto que gestiona todo en la navegación (ir, volver)
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login){
        //Para poder definir las Screens debemos poner composable<nombreScreen>
        composable<Login>{
            //Como tiene como parámetro un landa en lugwr de () ponemos {}
            LoginScreen{ navController.navigate(Home) }
        }
        composable<Home>{
            //Como es una clase data class tenemos que inicializarla
            HomeScreen{name -> navController.navigate(Detail(name =  name))}
        }
        //BackStack contiene información sobre la pantalla actual, incluyendo sus argumentos de navegación.
        // Esto permite extraer los parámetros pasados a la pantalla Detail.
        composable<Detail>{ backStackEntry ->
            // it es lo que nos devuelve esta función composable<Detail>{
            val detail:Detail = backStackEntry.toRoute()
            // Si quiero volver atrás es tan fácil como usar navController.popBackStack,
            //{ navController.popBackStack() }
            // pero si quiero llevarlo a una página que está más atrás hay que usar popUpTo
            DetailScreen(detail.name){ navController.navigate(Login){
                // Esto borra toda la pila de pantallas stack se limpia hasta el Login si pones el
                // inclusive a true, también limpia el login (Ya no hay más vistas en el stack
                // y si intentas volver atrás se sale de la app)
                popUpTo<Login>{inclusive = true}
            } }
        }
    }
}