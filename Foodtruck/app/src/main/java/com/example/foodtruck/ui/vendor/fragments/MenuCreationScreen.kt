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

class MenuCreationScreen: DialogFragment(), Toolbar.OnMenuItemClickListener, View.OnClickListener, FoodDialog.FoodItemReceiver {

    var listener: MenuItemReceiver? = null

    //private lateinit var alertDialogView: View
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

        menuListAdapter = MenuListAdapter(currentMenu, this)
        recycler_view.apply{
            adapter = menuListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onClick(view: View?) {
        val foodDialog = FoodDialog()
        foodDialog.listener = this
        foodDialog.show(fragmentManager!!, "food adder")
    }

    override fun receiveFoodItem(foodItem: FoodItem, tag: String, position: Int?) {
        if(tag == "food adder") {
            currentMenu.menuItemList.add(foodItem)
        } else{
            currentMenu.menuItemList[position!!] = foodItem
        }
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