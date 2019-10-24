package com.example.foodtruck.data.source.local.model

import java.io.Serializable

data class NewUser(
    val email: String,
    val city: City,
    val username: String,
    val accountType: AccountType
) {
    private fun cityToString(): String {
        return when(city) {
            City.ChicagoIL -> {
                "Chicago,IL"
            }
            City.BloomingtonIN -> {
                "Bloomington,IN"
            }
            City.NewYorkCityNY -> {
                "NewYork,NY"
            }
            City.BostonMA -> {
                "Boston,MA"
            }
        }
    }
    private fun accountTypeToString(): String {
        return when(accountType) {
            AccountType.Foodie -> {
                "foodie"
            }
            AccountType.Vendor -> {
                "vendor"
            }
        }
    }
    fun getFields(): HashMap<String, Any> {
        return hashMapOf(
            "email" to email,
            "username" to username,
            "city" to cityToString(),
            "accountType" to accountTypeToString()
        )
    }
}