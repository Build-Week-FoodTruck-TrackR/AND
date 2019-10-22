package com.example.foodtruck.ui.authentication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.example.foodtruck.R
import kotlinx.android.synthetic.main.fragment_authentication_main.*


class AuthenticationMainFragment : Fragment() {

    private val toSignupFragment by lazy {
        AuthenticationMainFragmentDirections
            .actionAuthenticationMainFragmentToSignupFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authentication_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_in_button.setOnClickListener {
            TODO()
        }
        sign_up_button.setOnClickListener {
            view.findNavController().navigate(toSignupFragment)
        }
    }
}
