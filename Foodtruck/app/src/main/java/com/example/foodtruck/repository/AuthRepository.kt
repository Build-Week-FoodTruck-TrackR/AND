package com.example.foodtruck.repository

import androidx.lifecycle.LiveData
import com.example.foodtruck.data.source.local.model.entities.User
import com.example.foodtruck.data.source.local.room.dao.UserDao
import java.util.concurrent.TimeUnit

//The DAO is passed into the repository as opposed to the whole database. This is because you only need access to the DAO, since it contains all the read/write methods for the database. There's no need to expose the entire database to the repository.
class AuthRepository(private val userDao: UserDao) {

    /*private fun refreshUser(userId: String) {
        executor.execute {
            val userExists = userDao.hasUser(FRESH_TIMEOUT)
            if(!userExists) {
                TODO()
            }
        }
    }*/
    /*suspend fun loadUser(userId: Int): LiveData<User> {
        return userDao.load(userId)
    }*/
    suspend fun saveUser(user: User) {
        userDao.insert(user)
    }
    suspend fun updateUser(user: User) {
        userDao.update(user)
    }
    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }
}