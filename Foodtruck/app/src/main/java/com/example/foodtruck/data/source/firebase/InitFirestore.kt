package com.example.foodtruck.data.source.firebase

import com.example.foodtruck.data.source.local.model.AccountType
import com.example.foodtruck.data.source.local.model.City
import com.example.foodtruck.data.source.local.model.firebase_models.User
import com.google.firebase.firestore.FirebaseFirestore

class InitFirestore {

    companion object {
        val initFirestoreInstance = InitFirestore()
    }

    private val db = FirebaseFirestore.getInstance()

    fun addUserToDatabase(user: User) {
        val city: String = user.getCityAsString()
        val accountType: String = user.getAccountTypeAsString()
        val fields = hashMapOf(
            "city" to city,
            "accountType" to accountType,
            "username" to user.username,
            "email" to user.email
        )
        db.collection("users").document(user.userId).set(fields)
    }
}

fun User.getCityAsString(): String {
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

fun User.getAccountTypeAsString(): String {
    return when (this.accountType) {
        AccountType.Foodie -> {
            "foodie"
        }
        AccountType.Vendor -> {
            "vendor"
        }
    }
}
