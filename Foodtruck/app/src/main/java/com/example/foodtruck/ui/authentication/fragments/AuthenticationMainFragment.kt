package com.example.foodtruck.ui.authentication.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.AuthenticationState
import com.example.foodtruck.ui.authentication.AuthenticationViewModel
import kotlinx.android.synthetic.main.fragment_authentication_main.*

class AuthenticationMainFragment : Fragment() {

    private lateinit var action: NavDirections
    private lateinit var authViewModel: AuthenticationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authentication_main, container, false)
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
                AuthenticationState.Authenticated -> AuthenticationMainFragmentDirections.actionAuthenticationMainFragmentToVendorActivity()
                AuthenticationState.Failed -> return@Observer
            }
        }
        authViewModel.authenticationState.observe(this, authStateObserver)

        sign_in_button.setOnClickListener {
            if(!authentication_email_edit_text.text.isNullOrBlank() && !authentication_password_edit_text.text.isNullOrBlank()) {
                authViewModel.authenticateUser(authentication_email_edit_text.text.toString(), authentication_password_edit_text.text.toString())
            }

            action = AuthenticationMainFragmentDirections.actionAuthenticationMainFragmentToVendorActivity()
            view.findNavController().navigate(action)
        }
    }
}
