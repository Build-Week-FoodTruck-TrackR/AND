package com.example.foodtruck.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.foodtruck.data.common.BaseDao
import com.example.foodtruck.data.source.local.model.entities.User

@Dao
interface UserDao : BaseDao<User> {

   @Query("SELECT * FROM users WHERE user_id = :userId")
   suspend fun load(userId: Int) : User
}