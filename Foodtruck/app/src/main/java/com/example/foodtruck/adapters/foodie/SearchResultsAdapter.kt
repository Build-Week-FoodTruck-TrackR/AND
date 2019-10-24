package com.example.foodtruck.adapters.foodie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.SaveableFoodtruck
import com.example.foodtruck.util.centeredToastMessage
import kotlinx.android.synthetic.main.search_results_layout.view.*

class SearchResultsAdapter(val data: MutableList<SaveableFoodtruck>): RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val favoriteButton = view.img_btn_favorite
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_results_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val foodtruck = data[position]

        holder.favoriteButton.setOnClickListener {
            if(!foodtruck.isFavorited){
                holder.view.context.centeredToastMessage("You have favorited ${data[position].name}")
                (it as ImageButton).setImageDrawable(holder.view.context.getDrawable(R.drawable.ic_favorite_red_24dp))
            } else{
                holder.view.context.centeredToastMessage("You have unfavorited ${data[position].name}")
                (it as ImageButton).setImageDrawable(holder.view.context.getDrawable(R.drawable.ic_favorite_border_black_24dp))
            }
        }
    }
}
