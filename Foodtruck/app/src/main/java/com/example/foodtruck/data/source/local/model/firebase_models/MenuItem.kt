package com.example.foodtruck.data.source.local.model.firebase_models

import android.graphics.Bitmap

data class MenuItem(
    val itemName: String,
    val description: String,
    val photoUris: List<String>
)