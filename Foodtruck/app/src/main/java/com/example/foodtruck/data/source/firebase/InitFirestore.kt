package com.example.foodtruck.data.source.firebase

import com.example.foodtruck.data.source.local.model.NewUser
import com.example.foodtruck.data.source.local.model.firebase_models.FoodieAccount
import com.google.firebase.firestore.FirebaseFirestore

class InitFirestore {

    companion object {
        val instance = InitFirestore()
    }

    private val db = FirebaseFirestore.getInstance()

    fun addUserToDatabase(newUser: NewUser, userId: String) {

        val userRef = db.collection("users").document(userId)
        db.runBatch { batch ->
            batch.set(userRef, newUser.getFields())
        }
    }

    fun createFoodieAccount(foodieAccount: FoodieAccount, userId: String) {

        val vendorAccountRef = db.collection("account_types/vendor_accounts").document(userId)
        db.runBatch { batch ->
            TODO()
        }
    }
}



