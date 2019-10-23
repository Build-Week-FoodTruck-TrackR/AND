package com.example.foodtruck.data.source.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthSource {

    companion object {
        val firebaseAuthSourceInstance = FirebaseAuthSource()
    }

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String): String? {

        firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return firebaseAuth.currentUser?.uid ?: throw FirebaseAuthException("", "")
    }

    suspend fun createAccountWithEmailAndPassword(email: String, password: String): String? {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return firebaseAuth.currentUser?.uid ?: throw FirebaseAuthException("", "")
    }

    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}