package com.example.foodtruck.ui.foodie.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.foodtruck.R
import kotlinx.android.synthetic.main.fragment_favorites.*

class Favorites : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    private fun setupRecyclerView(){
        recycler_view_favorites.apply{

        }
    }
}
