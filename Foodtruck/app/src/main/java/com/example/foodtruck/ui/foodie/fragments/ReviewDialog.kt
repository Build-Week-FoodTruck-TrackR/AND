package com.example.foodtruck.ui.foodie.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foodtruck.R
import com.example.foodtruck.util.createAlert
import com.google.android.material.textfield.TextInputEditText

class ReviewDialog: DialogFragment() {

    val alertView by lazy{
        LayoutInflater.from(context).inflate(R.layout.reviews_layout, null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val et = alertView.findViewById<TextInputEditText>(R.id.et_user_review)
        val rating = alertView.findViewById<RatingBar>(R.id.ratingBar2)

        alertView.findViewById<Button>(R.id.btn_submit).setOnClickListener {

            if(et.text.toString() != ""){
                //get userid, description and rating to form review, post it.
            }
        }

        alertView.findViewById<ImageView>(R.id.img_close).setOnClickListener {
            if(et.text.toString() != ""){
                context!!.createAlert({d, i-> d.dismiss()}, {d, i -> }).show()
            } else{
                dismiss()
            }
        }

        val alertDialog = AlertDialog.Builder(context!!)
            .setCancelable(true)
            .setView(alertView)

        return alertDialog.create()
    }


}