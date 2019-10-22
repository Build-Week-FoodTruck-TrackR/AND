package com.example.foodtruck.ui.vendor.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.Foodtruck
import kotlinx.android.synthetic.main.fragment_foodtruck_screen.*

class FoodtruckScreen : Fragment(), FoodtruckCreationScreen.FoodtruckReceiver {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foodtruck_screen, container, false)
    }

    override fun onStart() {
        super.onStart()
        floating_action_btn.setOnClickListener {
            val f = FoodtruckCreationScreen()
            f.show(fragmentManager!!, "OK")
        }
    }

    override fun receiveFoodtruck(foodtruck: Foodtruck) {
        //add to list and recyclerview
    }
}
