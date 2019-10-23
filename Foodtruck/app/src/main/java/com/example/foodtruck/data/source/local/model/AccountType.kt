package com.example.foodtruck.data.source.local.model

sealed class AccountType {
    object Foodie : AccountType()
    object Vendor : AccountType()
}