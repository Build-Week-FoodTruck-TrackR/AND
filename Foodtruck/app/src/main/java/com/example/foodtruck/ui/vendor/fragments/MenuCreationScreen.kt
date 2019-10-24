package com.example.foodtruck.ui.vendor.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodtruck.R
import com.example.foodtruck.adapters.vendor.MenuListAdapter
import com.example.foodtruck.data.source.local.model.FoodItem
import com.example.foodtruck.data.source.local.model.Menu
import com.example.foodtruck.util.createAlert
import com.example.foodtruck.util.showShortToastMessage
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fullscreen_dialog_menu_creation.*
import java.lang.NumberFormatException
import kotlin.properties.Delegates

class MenuCreationScreen: DialogFragment(), Toolbar.OnMenuItemClickListener, View.OnClickListener {

    var listener: MenuItemReceiver? = null

    private lateinit var alertDialogView: View
    private lateinit var menuListAdapter: MenuListAdapter
    private var currentMenu  = Menu(mutableListOf())

   interface MenuItemReceiver{
       fun receivePotentialMenu(menu: Menu?)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fullscreen_dialog_menu_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        top_toolbar.setNavigationOnClickListener {
            context!!.createAlert({d, i -> dismiss() }, {d, i-> }).show() //stay on the fragment
        }

        top_toolbar.setOnMenuItemClickListener(this)

        floating_action_btn.setOnClickListener(this)
    }

    private fun setupRecyclerView() {

        val bundle = arguments
        if(bundle != null){
            currentMenu = arguments!!.get("menu_edit") as Menu
        }

        menuListAdapter = MenuListAdapter(currentMenu)
        recycler_view.apply{
            adapter = menuListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onClick(view: View?) {

        alertDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_food_adder, null)

        val alert = context!!.createAlert({d, i-> }, {d, i -> d.dismiss()}, null, "Submit", "Cancel", alertDialogView)
        alert.show()

        val positiveListener : (View) -> (Unit) = { view ->
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
                    val foodItem = FoodItem(price, foodItemName)

                    if (foodItemDescription != "") {
                        foodItem.foodDescription = foodItemDescription
                    }
                    //add to recyclerview
                    addFoodItem(foodItem)
                    alert.dismiss()
                }
            } catch (n: NumberFormatException) {
                if(foodItemPrice == ""){
                    priceInputLayout.error = "Menu item must have a price."
                } else{
                    priceInputLayout.error = "Price must be a number value."
                }
            }
        }
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(positiveListener)
    }

    private fun addFoodItem(foodItem: FoodItem) {
        currentMenu.menuItemList.add(foodItem)
        menuListAdapter.notifyDataSetChanged()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(currentMenu.menuItemList.size == 0){
            context!!.showShortToastMessage("Must have food items to save menu.")
        } else{
            listener!!.receivePotentialMenu(currentMenu)
            dismiss()
        }
        return true
    }
}