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
    private val host by lazy {
        NavHostFragment.create(R.navigation.foodie_graph)
    }

    private lateinit var foodieViewModel: FoodieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodie)

        foodieViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(FoodieViewModel::class.java)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.foodie_nav_host_fragment, host)
            .setPrimaryNavigationFragment(host).commit()

        setSupportActionBar(findViewById(R.id.toolbar))



        setupActionBar()
        setupSideNavigation()
    }

    private fun setupSideNavigation() {
        NavigationUI.setupWithNavController(nav_view, TODO())
    }

    private fun setupActionBar() {
        NavigationUI.setupActionBarWithNavController(this, TODO(), drawer_layout)
    }
}
