package com.example.foodtruck.data.source.local.model.firebase_models

import com.google.type.LatLng

data class VendorAccount(
    val vendorId: String,
    val businessName: String,
    val businessOwnerFirstName: String,
    val businessOwnerLastName: String,
    val avgRating: Float = 3.0f,
    val isOpenForBusiness: Boolean = false,
    val lastLocation: LatLng? = null,
    val reviews: List<Review>? = null,
    val menuItems: List<MenuItem>? = null,
    val cusuineType: String? = null,
    val operationTimes: Nothing = TODO()
) {
    fun getVendorInfoField() {
        hashMapOf(
            "businessName" to businessName,
            "cusuineType" to cusuineType,
            "averageRating" to avgRating,
            "ownersName" to hashMapOf(
                "firstName" to businessOwnerFirstName,
                "lastName" to businessOwnerLastName
            )
        )
    }
}
