package com.example.foodtruck.data.source.local.model.firebase_models

data class FoodieAccount(
    val mapDisplayRange: Int,
    val favoriteVendors: List<String>, // simply stores vendor uid as a string
    val reviews: List<Review> // holds a list of users reviews
)