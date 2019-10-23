package com.example.foodtruck.ui.authentication

import android.app.Application
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.NavHostFragment
import com.example.foodtruck.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class AuthenticationActivity : AppCompatActivity() {


    private val host by lazy {
        NavHostFragment.create(R.navigation.auth_nav_graph)
    }

    private lateinit var firebaseApp: FirebaseApp
    private lateinit var authViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        firebaseApp = FirebaseApp.getInstance()
        authViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(AuthenticationViewModel::class.java)



        supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_nav_host_fragment_container, host)
            .setPrimaryNavigationFragment(host).commit()


    }
}
