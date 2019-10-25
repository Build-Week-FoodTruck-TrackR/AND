package com.example.foodtruck.ui.authentication.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.AccountType
import com.example.foodtruck.data.source.local.model.AuthenticationState
import com.example.foodtruck.data.source.local.model.City
import com.example.foodtruck.ui.authentication.AuthenticationViewModel
import com.example.foodtruck.util.setVisibilityToGone
import com.example.foodtruck.util.setVisibilityToVisible
import com.example.foodtruck.util.showShortToastMessage
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    private lateinit var authViewModel: AuthenticationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
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

        vendor_register_button.setOnClickListener {
            if(isVendorRegistrationFormValid()) {
                authViewModel.registerUser(
                   vendor_email_edit_text.text.toString(),
                    vendor_password_edit_text.text.toString(),
                    vendor_username_edit_text.text.toString(),
                    City.BloomingtonIN,
                    AccountType.Vendor,
                    vendor_firstname_edit_text.text.toString(),
                    vendor_lastname_edit_text.text.toString(),
                    vendor_businessname_edit_text.text.toString()
            )
            }
            if(authViewModel.authenticationState.value == AuthenticationState.Authenticated) {
                SignupFragmentDirections.actionSignupFragmentToVendorActivity()
            }
        }
        foodie_register_button.setOnClickListener {
            if(isFoodieRegistrationFormValid()) {
                authViewModel.registerUser(foodie_email_edit_text.text.toString(), foodie_password_edit_text.text.toString(), foodie_username_edit_text.text.toString(), City.BloomingtonIN, AccountType.Foodie)
            }
            if(authViewModel.authenticationState.value == AuthenticationState.Authenticated) {
                SignupFragmentDirections.actionSignupFragmentToFoodieActivity()
            }
        }

        account_type_button_toggle_group.addOnButtonCheckedListener { group, checkedId, isChecked ->

            when (isChecked) {
                true -> {
                    divider.setVisibilityToVisible()
                    when (checkedId) {
                        foodie_button.id -> {
                            expandFoodieForm()
                        }
                        vendor_button.id -> {
                            expandVendorForm()
                        }
                    }
                }
                false -> {
                    val checkedButtons = group.checkedButtonIds
                    when (checkedButtons.isNotEmpty()) {
                        true -> {
                            @Suppress("SimplifyBooleanWithConstants")
                            if (checkedButtons.contains(foodie_button.id) == true) {
                                expandFoodieForm()
                            } else {
                                expandVendorForm()
                            }
                        }
                        false -> {
                            collapseAllExpandables()
                        }
                    }
                }
            }
        }
    }

    private fun expandFoodieForm() {
        foodie_account_form_constraintlayout.setVisibilityToVisible()
        vendor_account_form_constrainlayout.setVisibilityToGone()
        context?.showShortToastMessage("Create Foodie Account")
    }

    private fun expandVendorForm() {
        vendor_account_form_constrainlayout.setVisibilityToVisible()
        foodie_account_form_constraintlayout.setVisibilityToGone()
        context?.showShortToastMessage("Create Vendor Account")
    }

    private fun collapseAllExpandables() {
        foodie_account_form_constraintlayout.setVisibilityToGone()
        vendor_account_form_constrainlayout.setVisibilityToGone()
        divider.setVisibilityToGone()
    }
    private fun isFoodieRegistrationFormValid(): Boolean {
        return !foodie_password_edit_text.text.isNullOrBlank() && !foodie_email_edit_text.text.isNullOrBlank() && !foodie_username_edit_text.text.isNullOrBlank()
    }
    private fun isVendorRegistrationFormValid() : Boolean {
       val textfields = listOf(
           vendor_email_edit_text.text,
           vendor_username_edit_text.text,
           vendor_password_edit_text.text,
           vendor_firstname_edit_text.text,
           vendor_lastname_edit_text.text,
           vendor_businessname_edit_text.text
       )
        val isAFieldBlank = textfields.any {
            it.isNullOrEmpty()
        }
        return !isAFieldBlank
    }
}
