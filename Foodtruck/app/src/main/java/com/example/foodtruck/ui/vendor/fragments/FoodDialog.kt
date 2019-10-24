package com.example.foodtruck.ui.vendor.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.FoodItem
import com.example.foodtruck.util.createAlert
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class FoodDialog: DialogFragment() {

    lateinit var listener: FoodItemReceiver
    lateinit var alertDialogView: View
    private var currentPositionOfFoodItem: Int? = null

    interface FoodItemReceiver{
        fun receiveFoodItem(foodItem: FoodItem, tag: String, position: Int?)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        alertDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_food_adder, null)

        val alert = context!!.createAlert({d, i-> }, {d, i -> d.dismiss()}, null, "Submit", "Cancel", alertDialogView)

        return alert
    }

    override fun show(manager: FragmentManager, tag: String?) {

        super.show(manager, tag)
        fragmentManager!!.executePendingTransactions()

        val bundle = arguments
        if(bundle != null){
            val foodItem = bundle.get("foodItem") as FoodItem
            alertDialogView.findViewById<TextInputEditText>(R.id.et_fooditem_name).setText(foodItem.foodName)
            alertDialogView.findViewById<TextInputEditText>(R.id.et_price).setText(foodItem.price.toString())
            alertDialogView.findViewById<TextInputEditText>(R.id.et_description).setText(foodItem.foodDescription)

            currentPositionOfFoodItem = bundle.get("foodItemPosition") as Int
        }

        val positiveListener : (View)->(Unit) = {
                view->
            val foodItemName = alertDialogView.findViewById<TextInputEditText>(R.id.et_fooditem_name).text.toString()
            val foodItemPrice = alertDialogView.findViewById<TextInputEditText>(R.id.et_price).text.toString()
            val foodItemDescription = alertDialogView.findViewById<TextInputEditText>(R.id.et_description).text.toString()

            val priceInputLayout = alertDialogView.findViewById<TextInputLayout>(R.id.price_input)
            priceInputLayout.error = "Menu item must have a price."

            val nameInputLayout = alertDialogView.findViewById<TextInputLayout>(R.id.text_input_menu_name)

            try {
                if (foodItemName == ""){
                    nameInputLayout.error = "Menu item must have a name."
                }

                val price = foodItemPrice.toDouble()

                if(foodItemName != "") {
                    val foodItem = FoodItem(price, foodItemName, foodItemDescription)
                    listener.receiveFoodItem(foodItem, getTag()!!, currentPositionOfFoodItem)
                    dismiss()
                }
            } catch (n: NumberFormatException) {
                if(foodItemPrice == ""){
                    priceInputLayout.error = "Menu item must have a price."
                } else{
                    priceInputLayout.error = "Price must be a number value."
                }
            }
        }
        (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(positiveListener)
    }
}