package com.example.foodtruck.data.source.local.model

import java.io.Serializable

data class NewUser(
    val email: String,
    val password: String,
    val city: City,
    val accountType: AccountType
)