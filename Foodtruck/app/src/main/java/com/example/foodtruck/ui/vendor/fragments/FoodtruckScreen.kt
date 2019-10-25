package com.example.foodtruck.ui.vendor.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.foodtruck.R
import com.example.foodtruck.adapters.vendor.FoodtruckListAdapter
import com.example.foodtruck.data.source.local.model.Foodtruck
import kotlinx.android.synthetic.main.fragment_foodtruck_screen.*

class FoodtruckScreen : Fragment(), FoodtruckCreationScreen.FoodtruckReceiver {

    private val foodtruckList = mutableListOf<Foodtruck>()
    private lateinit var foodtruckListAdapter: FoodtruckListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foodtruck_screen, container, false)
    }

    override fun onStart() {
        super.onStart()

        setupRecyclerView()

        floating_action_btn.setOnClickListener {
            val f = FoodtruckCreationScreen()
            f.listener = this
            f.show(fragmentManager!!, "OK")
        }
    }

    private fun setupRecyclerView(){
        foodtruckListAdapter = FoodtruckListAdapter(foodtruckList, this)
        foodtruck_recyclerview.apply{
            adapter = foodtruckListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun receiveFoodtruck(foodtruck: Foodtruck, tag: String, position: Int?) {
        //add to list and recyclerview
        if(tag == "editingFoodTruck"){
            foodtruckList.removeAt(position!!)
            foodtruckList.add(position, foodtruck)
        } else{
            foodtruckList.add(foodtruck)
        }
        foodtruckListAdapter.notifyDataSetChanged()
    }
}
