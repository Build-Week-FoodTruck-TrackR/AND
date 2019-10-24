package com.example.foodtruck.data.source.local.model.firebase_models

import com.google.type.LatLng

data class VendorAccount(
    val vendorId: String,
    val businessName: String,
    val businessOwnerFirstName: String,
    val businessOwnerLastName: String,
    val avgRating: Double = 3.0,
    val isOpenForBusiness: Boolean = false,
    val lastLocation: LatLng? = null,
    val reviews: List<Review>? = null,
    val menuItems: List<MenuItem>? = null,
    val operationTimes: Nothing = TODO()
)
