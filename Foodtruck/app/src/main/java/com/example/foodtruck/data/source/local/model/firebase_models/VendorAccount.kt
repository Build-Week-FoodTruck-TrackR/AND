package com.example.foodtruck.data.source.local.model.firebase_models

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.type.LatLng

@IgnoreExtraProperties
data class VendorAccount(
    val vendorId: String,
    val businessName: String,
    val businessOwnerFirstName: String,
    val businessOwnerLastName: String,
    val avgRating: Float = 3.0f,
    val vendorLogoUri: String,
    val isOpenForBusiness: Boolean = false,
    val lastLocation: LatLng? = null,
    val reviews: List<Review>? = null,
    val menuItems: List<MenuItem>? = null,
    val cusuineType: String? = null,
    val operationTimes: Nothing = TODO()
) {
    inner class VendorInfo() {
        fun getVendorInfoFields() {
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

}
