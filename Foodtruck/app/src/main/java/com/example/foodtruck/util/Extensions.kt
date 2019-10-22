package com.example.foodtruck.util

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

fun Context.createAlert(positiveListener: (DialogInterface, Int)->Unit, negativeListener: (DialogInterface, Int)->Unit,
                        message: String = "Are you sure you want to cancel?", positiveButtonText: String = "YES", negativeButtonText: String = "NO"){
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(positiveButtonText, positiveListener)
        .setNegativeButton("NO", negativeListener)
        .create()
        .show()
}