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
import com.example.foodtruck.ui.vendor.fragments.MenuCreationScreen
import com.example.foodtruck.util.createAlert

class FoodTruckDetailsDialog: DialogFragment() {

    private lateinit var alertDialogView: View
    private lateinit var saveableFoodtruck: SaveableFoodtruck

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
            saveableFoodtruck = bundle.get("foodTruckMarker") as SaveableFoodtruck

            alertDialogView.findViewById<TextView>(R.id.tv_foodtruck_name).text = saveableFoodtruck.name
            alertDialogView.findViewById<Button>(R.id.rating_bar).text = "${saveableFoodtruck.averageReviewScore}\\t\\t\\t\\t ${saveableFoodtruck.allReviews.size}"

            TODO("NEED TO DETERMINE WHETHER THE OPERATIONAL HOURS FOR TODAY MEANS ITS CURRENTLY OPEN")
        }

        alertDialogView.findViewById<Button>(R.id.button).setOnClickListener {
            //reveal operating hours
            val alertDialogView = LayoutInflater.from(context).inflate(R.layout.operational_hours_layout, null)

            val a = AlertDialog.Builder(context)
                .setCancelable(true)
                .setView(alertDialogView)
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

        alertDialogView.findViewById<Button>(R.id.btn_view_menu).setOnClickListener {
            val m = MenuCreationScreen()
            val bundle = Bundle()
            bundle.putSerializable("", "uneditable")
        }

        fragmentManager!!.executePendingTransactions()
        (dialog as AlertDialog).setView(alertDialogView)
        super.show(manager, tag)
    }
}