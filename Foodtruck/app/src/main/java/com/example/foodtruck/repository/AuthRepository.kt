package com.example.foodtruck.repository

import androidx.lifecycle.LiveData
import com.example.foodtruck.data.source.local.model.entities.User
import com.example.foodtruck.data.source.local.room.dao.UserDao
import com.example.foodtruck.data.source.remote.auth.FirebaseAuthSource
import com.google.firebase.auth.FirebaseUser
import java.util.concurrent.TimeUnit

//The DAO is passed into the repository as opposed to the whole database. This is because you only need access to the DAO, since it contains all the read/write methods for the database. There's no need to expose the entire database to the repository.
class AuthRepository(private val userDao: UserDao) {

    private val firebaseAuthSource = FirebaseAuthSource.firebaseAuthSourceInstance

    suspend fun authenticateWithEmailAndPassword(email: String, password: String): String? {
        return firebaseAuthSource.signInWithEmailAndPassword(email, password)
    }

    suspend fun registerWithEmailAndPassword(email: String, password: String): String? {
        return firebaseAuthSource.createAccountWithEmailAndPassword(email, password)
    }
    fun isUserLoggedIn(): Boolean {
        return firebaseAuthSource.isUserLoggedIn()
    }

}