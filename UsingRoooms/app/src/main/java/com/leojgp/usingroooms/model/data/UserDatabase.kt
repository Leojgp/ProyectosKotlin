package com.leojgp.usingroooms.model.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leojgp.usingroooms.model.data.entities.UserEntity

@Database(entities = [UserEntity::class],version = 1)
abstract class UserDatabase: RoomDatabase(){
abstract val dao: UserDao
}