package com.example.foodtruck.data.source.local.model.firebase_models

data class VendorAccount(
    val vendorId: String,
    val businessName: String,
    val businessOwnerFirstName: String,
    val businessOwnerLastName: String,
    val isOpenForBusiness: Boolean,
    val lastLocation: String,
    val menuItems: List<VendorMenuItem>
)
