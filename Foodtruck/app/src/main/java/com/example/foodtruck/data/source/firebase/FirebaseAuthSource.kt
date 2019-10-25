package com.example.foodtruck.data.source.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await

class FirebaseAuthSource {

    companion object {
        val firebaseAuthSourceInstance =
            FirebaseAuthSource()
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
    fun logout(){
        firebaseAuth.signOut()
    }
    fun getCurrentUserId() : String? {
        return firebaseAuth.currentUser?.uid
    }
}