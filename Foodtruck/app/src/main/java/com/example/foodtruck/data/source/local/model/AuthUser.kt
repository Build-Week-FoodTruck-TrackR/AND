package com.example.foodtruck.data.source.local.model

import android.provider.ContactsContract

data class AuthUser(
    val username: String,
    val password: String,
    val email: String
)