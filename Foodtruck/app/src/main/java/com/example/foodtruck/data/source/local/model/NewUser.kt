package com.example.foodtruck.data.source.local.model

import java.io.Serializable

class NewUser(
    username: String,
    password: String,
    email: String,
    accountType: AccountType
) : Serializable