package com.example.foodtruck.ui.vendor.fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.foodtruck.R
import com.example.foodtruck.util.createAlert
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fullscreen_dialog_menu_creation.*

class MenuCreationScreen: DialogFragment() {

    private lateinit var listener: MenuItemReceiver

    interface MenuItemReceiver{
        fun receiveMenuItem(menu: Menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MenuItemReceiver) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
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
            //user pressed submit
            //(d as DialogFragment).view!!.findViewById<TextInputEditText>(

            if(true){

            }
        }

        val negativeListener: (DialogInterface, Int) -> Unit = {
            d, i ->
        }

        floating_action_btn.setOnClickListener{
            context!!.createAlert(positiveListener, negativeListener, "Add food item", "Submit", "Cancel", R.layout.dialog_food_adder)
        }
    }
}