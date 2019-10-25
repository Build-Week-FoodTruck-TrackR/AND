package com.example.foodtruck.data.source.local.model.firebase_models

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class MenuItem(
    val itemName: String,
    val description: String? = null,
    val quotedPrice: Double,
    val photoUris: String? = null
)