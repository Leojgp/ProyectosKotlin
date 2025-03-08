package com.leojgp.usingroooms.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leojgp.usingroooms.model.data.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:UserEntity)

    @Query("SELECT * FROM UserEntity ORDER BY id")
    suspend fun getUsers():List<UserEntity>
}