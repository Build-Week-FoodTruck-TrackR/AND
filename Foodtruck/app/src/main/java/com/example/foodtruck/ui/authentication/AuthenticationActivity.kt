package com.example.foodtruck.ui.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.foodtruck.R

class AuthenticationActivity : AppCompatActivity() {

    private val host by lazy {
        NavHostFragment.create(R.navigation.auth_nav_graph)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_nav_host_fragment_container, host)
            .setPrimaryNavigationFragment(host).commit()
    }
}
