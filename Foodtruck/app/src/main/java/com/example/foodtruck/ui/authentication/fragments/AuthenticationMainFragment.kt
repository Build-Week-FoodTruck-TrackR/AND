package com.example.foodtruck.ui.authentication.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

import com.example.foodtruck.R

class AuthenticationMainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authentication_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        view!!.findViewById<Button>(R.id.sign_up_button).setOnClickListener {
            val action = AuthenticationMainFragmentDirections.actionAuthenticationMainFragmentToSignupTypeFragment()
            it.findNavController().navigate(action)
        }

        view!!.findViewById<Button>(R.id.sign_in_button).setOnClickListener {
            val action = AuthenticationMainFragmentDirections.actionAuthenticationMainFragmentToFoodieActivity()
            it.findNavController().navigate(action)
        }

        view!!.findViewById<Button>(R.id.forgot_password_bttn).setOnClickListener {

        }
    }
}
