package com.example.foodtruck.adapters.vendor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.Foodtruck
import com.example.foodtruck.ui.vendor.fragments.FoodtruckCreationScreen
import com.example.foodtruck.ui.vendor.fragments.FoodtruckScreen
//import com.example.foodtruck.ui.vendor.fragments.FoodtruckScreen
import com.example.foodtruck.ui.vendor.fragments.MenuCreationScreen
import com.example.foodtruck.util.createAlert
import kotlinx.android.synthetic.main.foodtruck_item_layout.view.*


class FoodtruckListAdapter(val data: MutableList<Foodtruck>, val foodtruckScreen: FoodtruckScreen): RecyclerView.Adapter<FoodtruckListAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val foodTruckName = view.tv_foodtruck_name
        val foodTruckModel = view.tv_foodtruck_model
        val cancelButton = view.img_cancel_btn
        val editButton = view.img_edit_btn
        val viewMenuButton = view.btn_view_menu
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.foodtruck_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foodTruckName.text = data[position].name
        holder.foodTruckModel.text = data[position].model

        holder.cancelButton.setOnClickListener {
            holder.view.context!!.createAlert(
            {d, i -> d.dismiss()
                     data.removeAt(position)
                     this.notifyItemRemoved(position) }, {d, i-> }).show()
        }

        holder.editButton.setOnClickListener {
            val f = FoodtruckCreationScreen()
            f.listener = foodtruckScreen
            val bundle = Bundle()
            bundle.putSerializable("foodTruckToEdit", data[position])
            bundle.putInt("foodTruckToEditPosition", position)
            f.arguments = bundle
            f.show(foodtruckScreen.fragmentManager!!, "editingFoodTruck")
        }

        holder.viewMenuButton.setOnClickListener {
            val menuCreationScreen = MenuCreationScreen()
            val bundle = Bundle()
            bundle.putSerializable("uneditableMenu", data[position].menu)
            menuCreationScreen.arguments = bundle
            menuCreationScreen.show(foodtruckScreen.fragmentManager!!, "uneditableMenuView")
        }
    }
}
