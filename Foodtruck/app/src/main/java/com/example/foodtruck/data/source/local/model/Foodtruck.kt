package com.example.foodtruck.data.source.local.model

import java.io.Serializable


//this is the foodtruck we will post to the backend, does not have any current reviews
open class Foodtruck(val name: String, val model: String, val latitude: Double, val longitude: Double, var menu: Menu? = null) : Serializable

//when we use a GET request to see the food truck from the backend, we will get all the reviews other diners will have left it.
class SaveableFoodtruck(val allReviews: MutableList<Reviews>, name: String, model: String, latitude: Double, longitude: Double, menu: Menu? = null) : Foodtruck(name, model, latitude, longitude, menu)

//this is a list of reviews that we are getting back when we query the foodtruck
class Reviews(val score: Double, val description: String, val userName: String)





