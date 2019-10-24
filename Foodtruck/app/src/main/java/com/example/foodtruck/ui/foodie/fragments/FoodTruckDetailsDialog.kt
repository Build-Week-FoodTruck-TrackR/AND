package com.example.foodtruck.ui.foodie.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.SaveableFoodtruck
import com.example.foodtruck.util.createAlert

class FoodTruckDetailsDialog: DialogFragment() {

    private lateinit var alertDialogView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val a = AlertDialog.Builder(context)
            .setCancelable(true)
            .setPositiveButton("OK"){d, i->}

        return a.create()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        alertDialogView = LayoutInflater.from(context).inflate(R.layout.search_results_layout, null)

        val bundle = arguments
        if(bundle != null){
            val foodtruck = bundle.get("foodTruckMarker") as SaveableFoodtruck

            alertDialogView.findViewById<TextView>(R.id.tv_foodtruck_name).text = foodtruck.name
            alertDialogView.findViewById<Button>(R.id.rating_bar).text = "${foodtruck.averageReviewScore}\\t\\t\\t\\t ${foodtruck.allReviews.size}"
        }

        alertDialogView.findViewById<Button>(R.id.button).setOnClickListener {
            //reveal operating hours
            val a = AlertDialog.Builder(context)
                .setCancelable(true)
                .setView(R.layout.operational_hours_layout)
                .setOnCancelListener {
                    this.show(fragmentManager!!, "OK")
                }
                .setPositiveButton("OK"){ d, i->
                    this.show(fragmentManager!!, "OK")
                }
        }

        alertDialogView.findViewById<Button>(R.id.rating_bar).setOnClickListener {
            //bring us to review page

        }

        fragmentManager!!.executePendingTransactions()
        (dialog as AlertDialog).setView(alertDialogView)
        super.show(manager, tag)
    }
}