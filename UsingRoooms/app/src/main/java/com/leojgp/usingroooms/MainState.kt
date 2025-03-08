package com.leojgp.usingroooms

data class MainState (
    val name:String = "",
    var names:List<User> = emptyList()
)