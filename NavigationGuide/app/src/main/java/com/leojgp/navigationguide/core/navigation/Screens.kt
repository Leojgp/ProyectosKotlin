package com.leojgp.navigationguide.core.navigation

import kotlinx.serialization.Serializable

//Para poder usar estos objetos en la navegación tenemos que ponerle la etiqueta Serializable
@Serializable
object Login

@Serializable
object Home

//Si no mandamos parámetros podemos usar un objeto, sí mandamos parámetros tenemos que usar data class
@Serializable
data class Detail(val name:String)