package com.example.foodtruck.ui.vendor.fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.FoodItem
import com.example.foodtruck.util.createAlert
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.dialog_food_adder.*
import kotlinx.android.synthetic.main.fullscreen_dialog_menu_creation.*
import java.lang.NumberFormatException

class MenuCreationScreen: DialogFragment() {
//
//    private lateinit var listener: MenuItemReceiver
//
//    interface MenuItemReceiver{
//        fun receiveMenu(menu: Menu)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
        super.onCreate(savedInstanceState)
    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is MenuItemReceiver) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }

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
            val foodName = (d as DialogFragment).view!!.findViewById<TextInputEditText>(R.id.et_fooditem_name).text.toString()
            val foodPrice = (d as DialogFragment).view!!.findViewById<TextInputEditText>(R.id.et_price).text.toString()
            val foodDescription = (d as DialogFragment).view!!.findViewById<TextInputEditText>(R.id.et_description).text.toString()

            try{
                foodPrice.toDouble()

                if(foodName != "" && foodPrice != ""){
                    val menuItem = FoodItem(foodPrice.toDouble(), foodName, foodDescription)

                }
            } catch(n: NumberFormatException){
            }

        }

        val negativeListener: (DialogInterface, Int) -> Unit = {
            d, i ->
        }

        floating_action_btn.setOnClickListener{
            context!!.createAlert(positiveListener, negativeListener, "Submit", "Cancel", null,"Add your menu item", R.layout.dialog_food_adder)
        }
    }
}