package com.leojgp.usingroooms.model.data.repositories

import com.leojgp.usingroooms.User
import com.leojgp.usingroooms.model.data.UserDao
import com.leojgp.usingroooms.model.data.entities.UserEntity

class UserRepository(private val userDao: UserDao){
    suspend fun insertUsers(user: User){
        val entity = UserEntity(name = user.name)
        userDao.insertUser(entity)
    }
    suspend fun getUsers():List<User>{
        return userDao.getUsers().map { user->
            User(name = user.name)
        }
    }
}
