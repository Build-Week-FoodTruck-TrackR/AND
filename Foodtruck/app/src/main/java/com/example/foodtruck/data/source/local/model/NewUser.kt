package com.example.foodtruck.data.source.local.model

import java.io.Serializable

class NewUser(
    val username: String,
    val password: String,
    val email: String,
    val accountType: AccountType
) : Serializable {
    fun getAccountType() : String {
        return when(accountType) {
            is AccountType.Foodie -> "USER_ACCOUNT"
            is AccountType.Vendor -> "VENDOR_ACCOUNT"
        }
    }
}