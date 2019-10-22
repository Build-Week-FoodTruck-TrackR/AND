package com.example.foodtruck.util

import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog

fun Context.createAlert(positiveListener: (DialogInterface, Int)->Unit, negativeListener: (DialogInterface, Int)->Unit,
                        message: String = "", positiveButtonText: String = "YES", negativeButtonText: String = "NO", view: View? = null){
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(positiveButtonText, positiveListener)
        .setNegativeButton(negativeButtonText, negativeListener)
        .setView(view!!)
        .create()
        .show()
}