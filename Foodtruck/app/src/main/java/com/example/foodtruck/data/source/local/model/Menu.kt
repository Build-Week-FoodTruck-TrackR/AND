package com.example.foodtruck.data.source.local.model

import java.io.Serializable

class Menu(val menuItemList: MutableList<FoodItem>) : Serializable

class FoodItem(val price: Double, val foodName: String, var foodDescription: String): Serializable

