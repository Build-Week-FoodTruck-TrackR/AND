package com.example.foodtruck.data.source.firebase

import com.example.foodtruck.data.source.local.model.NewUser
import com.google.firebase.firestore.FirebaseFirestore

class InitFirestore {


    private val db = FirebaseFirestore.getInstance()

    fun addUserToDatabase(newUser: NewUser, userId: String) {

        val userRef = db.collection("users").document(userId)
        db.runBatch { batch ->
            batch.set(userRef, newUser.getFields())
        }
    }
}



