package com.example.foodtruck.ui.vendor.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.FoodItem
import com.example.foodtruck.data.source.local.model.Menu
import com.example.foodtruck.util.createAlert
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fullscreen_dialog_menu_creation.*

class MenuCreationScreen: DialogFragment() {

    lateinit var listener: MenuItemReceiver

   interface MenuItemReceiver{
       fun receiveMenu(menu: Menu)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fullscreen_dialog_menu_creation, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val positiveListener : (DialogInterface, Int) -> Unit = {
            d, i ->
            //user pressed submit, add this menu item to recyclerview
        }

        val negativeListener: (DialogInterface, Int) -> Unit = {
            d, i ->
            d.dismiss()
        }

        top_toolbar.setNavigationOnClickListener {
            context!!.createAlert({d, i -> dismiss() }, {d, i-> }, "YES", "NO","Are you sure you want to cancel?\nYour changes will not be saved.") //stay on the fragment
        }

        floating_action_btn.setOnClickListener{
            context!!.createAlert(positiveListener, negativeListener, "Submit", "Cancel", null, R.layout.dialog_food_adder)
        }
    }
}