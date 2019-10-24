package com.example.foodtruck.adapters.vendor

/*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.Menu
import com.example.foodtruck.ui.vendor.fragments.MenuCreationScreen
import kotlinx.android.synthetic.main.operator_menu_item_layout.view.*

class MenuListAdapter(val menuData: Menu): RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val menuItemName = view.tv_food_title
        val menuItemPrice = view.tv_food_price
        val img_food_item = view.img_remove_food_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.operator_menu_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuData.menuItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.menuItemName.text = menuData.menuItemList[position].food
        holder.menuItemPrice.text = menuData.menuItemList[position].price.toString()

        holder.img_food_item.setOnClickListener {
            //remove this view from list
            menuData.menuItemList.removeAt(position)
            this.notifyItemRemoved(position)
        }

        holder.view.setOnClickListener {
            //update menu item entry
            val m = MenuCreationScreen()
        }
    }
}*/
