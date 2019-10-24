package com.example.foodtruck.data.source.local.model.firebase_models

data class FoodieAccount(
    val mapDisplayRange: Int,
    val favoriteVendors: List<VendorAccount>,
    val userReviews: List<FoodieReview>
)