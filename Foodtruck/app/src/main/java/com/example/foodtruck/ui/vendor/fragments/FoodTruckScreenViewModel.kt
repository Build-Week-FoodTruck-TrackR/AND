package com.example.foodtruck.ui.vendor.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodtruck.data.source.firebase.FirebaseAuthSource
import com.example.foodtruck.data.source.firebase.InitFirestore
import com.example.foodtruck.data.source.local.model.Foodtruck
import kotlinx.coroutines.launch

class FoodTruckScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val _foodTruck:  MutableLiveData<Foodtruck> by lazy {
        MutableLiveData<Foodtruck>()
    }
    val foodtruck: LiveData<Foodtruck>
        get() = _foodTruck

    val fireBaseAuth = FirebaseAuthSource.firebaseAuthSourceInstance
    val firestore = InitFirestore.instance

    fun getFoodTruckForVendor() = viewModelScope.launch {
        val userId = fireBaseAuth.getCurrentUserId()
        val foodTruck: Foodtruck
        if (userId != null) {
            _foodTruck.value = firestore.readVendorInfoForFoodTruck(userId)
        }
    }
}