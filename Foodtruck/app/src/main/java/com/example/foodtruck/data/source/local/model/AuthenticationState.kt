package com.example.foodtruck.data.source.local.model

sealed class AuthenticationState {
    object Authenticated : AuthenticationState()
    object Failed: AuthenticationState()
}