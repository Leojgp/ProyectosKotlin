package com.leojgp.usingroooms.model.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    var name:String
)
