package com.example.foodtruck.ui.authentication.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.foodtruck.R
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.view.*

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
                    view.vendor_button.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorGrey))
                    view.vendor_account_form_constrainlayout.visibility = View.GONE
                    view.divider.visibility = View.GONE
                }
                false -> {
                    view.vendor_button.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))
                    view.foodie_button.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorGrey))
                    view.vendor_account_form_constrainlayout.visibility = View.VISIBLE
                    view.foodie_account_form_constraintlayout.visibility = View.GONE
                    view.divider.visibility = View.VISIBLE
                }
            }
        }
        foodie_button.setOnClickListener {
            when (view.foodie_account_form_constraintlayout.isVisible) {
                true -> {
                    view.vendor_button.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorGrey))
                    view.foodie_account_form_constraintlayout.visibility = View.GONE
                    view.divider.visibility = View.GONE
                }
                false -> {
                    view.vendor_button.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorGrey))
                    view.vendor_button.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))
                    view.foodie_account_form_constraintlayout.visibility = View.VISIBLE
                    view.vendor_account_form_constrainlayout.visibility = View.GONE
                    view.divider.visibility = View.VISIBLE
                }
            }
        }
    }
}
