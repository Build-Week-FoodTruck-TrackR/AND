package com.example.foodtruck.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.foodtruck.R
import com.example.foodtruck.ui.authentication.fragments.SplashScreenFragment
import kotlinx.android.synthetic.main.activity_authentication.*

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
