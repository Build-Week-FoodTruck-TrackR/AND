package com.example.foodtruck.ui.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodtruck.R
import kotlinx.android.synthetic.main.activity_foodie.*

//
class FoodieActivity : AppCompatActivity() {
    //
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodie)

        setSupportActionBar(findViewById(R.id.toolbar))

        navController = Navigation.findNavController(this, R.id.foodie_nav_host_fragment)

        setupActionBar()
        setupSideNavigation()
    }

    private fun setupSideNavigation(){
        NavigationUI.setupWithNavController(nav_view, navController)
    }

    private fun setupActionBar(){
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
    }
}