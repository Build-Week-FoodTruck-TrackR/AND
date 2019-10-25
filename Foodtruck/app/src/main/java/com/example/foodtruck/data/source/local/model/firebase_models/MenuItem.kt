package com.example.foodtruck.data.source.local.model.firebase_models

data class MenuItem(
    val itemName: String,
    val description: String,
    val quotedPrice: Float,
    val photoUris: List<String>
)