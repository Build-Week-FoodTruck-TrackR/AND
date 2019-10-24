package com.example.foodtruck.ui.authentication.fragments

import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.AuthenticationState
import com.example.foodtruck.ui.authentication.AuthenticationViewModel


class SplashScreenFragment : Fragment() {


    private lateinit var authViewModel: AuthenticationViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel = activity.let {
            val appContext = activity?.applicationContext as Application
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(appContext)
                .create(AuthenticationViewModel::class.java)
        }

        val authStateObserver = Observer<AuthenticationState> { newAuthenticationState ->
            when (newAuthenticationState) {
                AuthenticationState.Authenticated -> toFoodieAction()
                AuthenticationState.Failed -> toAuthAction()
            }
        }

        authViewModel.authenticationState.observe(this, authStateObserver)
        authViewModel.signOutUser()
        authViewModel.isUserLoggedIn()

    }

    private fun toAuthAction() {
        val toAuthentication =
            SplashScreenFragmentDirections.actionSplashScreenFragmentToAuthentication()
        findNavController().navigate(toAuthentication)
    }

    private fun toFoodieAction() {
        val toFoodieActivity =
            SplashScreenFragmentDirections.actionSplashScreenFragmentToFoodieActivity2()
        findNavController().navigate(toFoodieActivity)
    }

    private fun toVendorAction() {
        val toVendorActivity =
            SplashScreenFragmentDirections.actionSplashScreenFragmentToVendorActivity2()
        findNavController().navigate(toVendorActivity)
    }
}
