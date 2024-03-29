package com.example.foodtruck.ui.authentication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.foodtruck.R
import kotlinx.android.synthetic.main.fragment_authentication_main.*

class AuthenticationMainFragment : Fragment() {

    private lateinit var action: NavDirections

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authentication_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_in_button.setOnClickListener {
            action = AuthenticationMainFragmentDirections.actionAuthenticationMainFragmentToFoodieActivity()
            view.findNavController().navigate(action)
        }

        sign_up_button.setOnClickListener {
            action =  AuthenticationMainFragmentDirections.actionAuthenticationMainFragmentToSignupFragment()
            view.findNavController().navigate(action)
        }
    }
}
