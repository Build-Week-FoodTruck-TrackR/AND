package com.example.foodtruck.data.source.local.model.firebase_models

import android.graphics.Bitmap

data class VendorMenuItem(
    val itemName: String,
    val photoUris: List<Bitmap>,
    val averageRating: Float
) {
}