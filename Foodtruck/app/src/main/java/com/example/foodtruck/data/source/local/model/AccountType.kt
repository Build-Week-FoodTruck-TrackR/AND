package com.example.foodtruck.data.source.local.model

sealed class AccountType {
    class Foodie : AccountType()
    class Vendor : AccountType()
}