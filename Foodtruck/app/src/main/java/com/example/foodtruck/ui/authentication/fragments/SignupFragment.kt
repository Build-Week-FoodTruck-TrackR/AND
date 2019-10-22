package com.example.foodtruck.ui.authentication.fragments

import android.content.Context
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

import com.example.foodtruck.R
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SignupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vendor_button.backgroundTintList = ContextCompat.getColorStateList(context!!,R.color.button_color_state_list)

        vendor_button.setOnClickListener {
            when(view.vendor_account_form_constrainlayout.isVisible) {
                true -> {
                    view.vendor_button.isPressed = false
                    view.vendor_account_form_constrainlayout.visibility = View.GONE
                    view.divider.visibility = View.GONE
                }
                false -> {
                    view.vendor_button.isPressed = true
                    view.vendor_account_form_constrainlayout.visibility = View.VISIBLE
                    view.divider.visibility = View.VISIBLE
                }
            }
        }
    }
}
