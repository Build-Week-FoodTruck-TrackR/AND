package com.example.foodtruck.ui.vendor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.Foodtruck
import com.example.foodtruck.ui.vendor.fragments.FoodtruckCreationScreen

class VendorActivity : AppCompatActivity(), FoodtruckCreationScreen.FoodtruckReceiver {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor)

        navigationController = Navigation.findNavController(this, R.id.nav_host)
    }

    override fun receiveFoodtruck(foodtruck: Foodtruck) {
        Log.i("Receiving", "Foodtruck")
    }
}
