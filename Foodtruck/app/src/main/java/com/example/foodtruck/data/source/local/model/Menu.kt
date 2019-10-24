package com.example.foodtruck.data.source.local.model

class Menu(val menuItemList: MutableList<FoodItem>)

class FoodItem(val price: Double, val foodName: String, var foodDescription: String? = null)

