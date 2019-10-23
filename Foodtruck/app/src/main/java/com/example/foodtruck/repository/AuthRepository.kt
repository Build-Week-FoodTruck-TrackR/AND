package com.example.foodtruck.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodtruck.data.source.local.model.entities.User
import com.example.foodtruck.data.source.local.room.dao.UserDao
//The DAO is passed into the repository as opposed to the whole database. This is because you only need access to the DAO, since it contains all the read/write methods for the database. There's no need to expose the entire database to the repository.
class AuthRepository(private val userDao: UserDao) {

    val cachedUsers: LiveData<List<User>> = userDao.getAllUsers()
    val currentUser: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    suspend fun insertUser(user: User) {
        userDao.insert(user)
    }
    suspend fun updateCurrentUser(userId: Int) {
        currentUser.value = userDao.getSingleUser(userId)
    }
}