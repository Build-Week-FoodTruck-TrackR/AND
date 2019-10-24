package com.example.foodtruck.data.source.local.model.firebase_models

import com.example.foodtruck.data.source.local.model.AccountType
import com.example.foodtruck.data.source.local.model.City

data class User (
    val userId: String,
    val email: String,
    val username: String,
    val accountType: AccountType,
    val city: City
)