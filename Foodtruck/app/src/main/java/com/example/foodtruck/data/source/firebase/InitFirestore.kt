package com.example.foodtruck.data.source.firebase

import com.example.foodtruck.data.source.local.model.AccountType
import com.example.foodtruck.data.source.local.model.City
import com.example.foodtruck.data.source.local.model.NewUser
import com.example.foodtruck.data.source.local.model.firebase_models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class InitFirestore {


    private val db = FirebaseFirestore.getInstance()

    fun addUserToDatabase(newUser: NewUser, userId: String) {
        val city: String = newUser.getCityAsString()
        val accountType: String = newUser.getAccountTypeAsString()

        val userRef = db.collection("users").document(userId)
        db.runBatch { batch ->
            batch.set(userRef, newUser)
        }
    }

    fun NewUser.getCityAsString(): String {
        return when (this.city) {
            City.ChicagoIL -> {
                "Chicago,IL"
            }
            City.BloomingtonIN -> {
                "Bloomington,IN"
            }
            City.NewYorkCityNY -> {
                "NewYork,NY"
            }
            City.BostonMA -> {
                "Boston,MA"
            }
        }
    }

    fun NewUser.getAccountTypeAsString(): String {
        return when (this.accountType) {
            AccountType.Foodie -> {
                "foodie"
            }
            AccountType.Vendor -> {
                "vendor"
            }
        }
    }
}
