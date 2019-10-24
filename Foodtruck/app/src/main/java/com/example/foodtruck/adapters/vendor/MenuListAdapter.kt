package com.example.foodtruck.adapters.vendor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.Menu
import com.example.foodtruck.ui.vendor.fragments.FoodDialog
import com.example.foodtruck.ui.vendor.fragments.MenuCreationScreen
import com.example.foodtruck.util.setVisibilityToGone
import com.example.foodtruck.util.setVisibilityToVisible
import kotlinx.android.synthetic.main.operator_menu_item_layout.view.*

class MenuListAdapter(val menuData: Menu, val menuCreationScreen: MenuCreationScreen): RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val menuItemName = view.tv_food_title
        val menuItemPrice = view.tv_food_price
        val remove_item = view.img_remove_food_item
        val edit_item = view.img_edit_food_item
        val menuItemDescription = view.tv_description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.operator_menu_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuData.menuItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.menuItemName.text = menuData.menuItemList[position].foodName
        holder.menuItemPrice.text = menuData.menuItemList[position].price.toString()
        holder.menuItemDescription.text = menuData.menuItemList[position].foodDescription

        holder.remove_item.setOnClickListener {
            //remove this view from list
            menuData.menuItemList.removeAt(position)
            this.notifyItemRemoved(position)
        }

        holder.edit_item.setOnClickListener {
            val foodDialog = FoodDialog()
            val bundle = Bundle()
            bundle.putInt("foodItemPosition", position)
            bundle.putSerializable("foodItem", menuData.menuItemList[position])
            foodDialog.arguments = bundle
            foodDialog.listener = menuCreationScreen
            foodDialog.show(menuCreationScreen.fragmentManager!!, "editFoodItem")
        }
    }
}