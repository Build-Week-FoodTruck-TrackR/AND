package com.example.foodtruck.repository


import com.example.foodtruck.data.source.firebase.FirebaseAuthSource
import com.example.foodtruck.data.source.firebase.InitFirestore
import com.example.foodtruck.data.source.local.model.NewUser

//The DAO is passed into the repository as opposed to the whole database. This is because you only need access to the DAO, since it contains all the read/write methods for the database. There's no need to expose the entire database to the repository.
class AuthRepository() {

    private val firebaseAuthSource = FirebaseAuthSource.firebaseAuthSourceInstance
    private val firestore = InitFirestore.instance

    suspend fun authenticateWithEmailAndPassword(email: String, password: String): String? {
        return firebaseAuthSource.signInWithEmailAndPassword(email, password)
    }

    suspend fun registerWithEmailAndPassword(email: String, password: String): String? {
        return firebaseAuthSource.createAccountWithEmailAndPassword(email, password)
    }

    suspend fun addNewAccount(
        userId: String,
        ownerFirstName: String? = null,
        ownerLastName: String? = null,
        businessName: String? = null,
        cuisineType: String? = null,
        logo: String? = null
    ) {
        firestore.writeNewAccount(userId, ownerFirstName, ownerLastName, businessName, cuisineType, logo)
    }

    fun addNewUserTodatabase(newUser: NewUser, userId: String) {
        firestore.writeNewUser(newUser, userId)
    }

    fun isUserLoggedIn(): Boolean {
        return firebaseAuthSource.isUserLoggedIn()
    }

    fun logout() {
        firebaseAuthSource.logout()
    }

}