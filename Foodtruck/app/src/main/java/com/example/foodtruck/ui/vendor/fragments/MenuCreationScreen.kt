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
import com.example.foodtruck.util.showShortToastMessage
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fullscreen_dialog_menu_creation.*
import java.lang.NumberFormatException

class MenuCreationScreen: DialogFragment() {

    lateinit var listener: MenuItemReceiver

    lateinit var alertDialogView: View

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

        val negativeListener: (DialogInterface, Int) -> Unit = {
            d, i ->
            d.dismiss()
        }

        top_toolbar.setNavigationOnClickListener {
            context!!.createAlert({d, i -> dismiss() }, {d, i-> }, "YES", "NO","Are you sure you want to cancel?\nYour changes will not be saved.").show() //stay on the fragment
        }

        floating_action_btn.setOnClickListener{
            alertDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_food_adder, null)
            val alert = context!!.createAlert({d, i-> }, negativeListener, "Submit", "Cancel", null, alertDialogView)
            alert.show()
            val positiveListener : (View) -> (Unit) = { view ->
                val foodItemName = alertDialogView.findViewById<TextInputEditText>(R.id.et_fooditem_name).text.toString()
                val foodItemPrice = alertDialogView.findViewById<TextInputEditText>(R.id.et_price).text.toString()
                val foodItemDescription = alertDialogView.findViewById<TextInputEditText>(R.id.et_description).text.toString()

                val priceInputLayout = alertDialogView.findViewById<TextInputLayout>(R.id.price_input)
                priceInputLayout.error = "Menu item must have a price."

                val nameInputLayout = alertDialogView.findViewById<TextInputLayout>(R.id.text_input_menu_name)

                try {
                    val price = foodItemPrice.toDouble()

                    if (foodItemName == ""){
                        nameInputLayout.error = "Menu item must have a name."
                    } else {
                        val foodItem = FoodItem(price, foodItemName)

                        if (foodItemDescription != "") {
                            foodItem.foodDescription = foodItemDescription
                        }
                        //add to recyclerview
                        alert.dismiss()
                    }
                } catch (n: NumberFormatException) {
                    if(foodItemPrice == ""){
                        priceInputLayout.error = "Menu item must have a price."
                    } else{
                        priceInputLayout.error = "Price must be a number value."
                    }
                    if(foodItemName == ""){
                        nameInputLayout.error = "Menu item must have a name."
                    }
                }
            }
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(positiveListener)
        }
    }
}