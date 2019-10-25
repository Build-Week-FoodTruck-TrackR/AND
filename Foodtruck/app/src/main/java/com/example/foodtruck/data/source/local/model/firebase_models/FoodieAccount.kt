package com.example.foodtruck.data.source.local.model.firebase_models

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class FoodieAccount(
    val mapDisplayRange: Int = 5,
    val favoriteVendors: List<String> // simply stores vendor uid as a string
    //val allReviews: List<Review> // holds a list of users reviews
)