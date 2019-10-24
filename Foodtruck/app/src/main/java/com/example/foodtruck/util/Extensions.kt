package com.example.foodtruck.util

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

//create an alert with two buttons
fun Context.createAlert(
    positiveListener: (DialogInterface, Int) -> Unit,
    negativeListener: (DialogInterface, Int) -> Unit,
    positiveButtonText: String,
    negativeButtonText: String,
    message: String? = null,
    layoutResId: Int? = null
) {
    val a = AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(positiveButtonText, positiveListener)
        .setNegativeButton(negativeButtonText, negativeListener)

    layoutResId?.let{
        a.setView(it)
    }
    message?.let{
        a.setMessage(it)
    }
        a.create()
        a.show()
}

// View Visibility
fun View.setVisibilityToVisible() {
    this.visibility = View.VISIBLE
}

fun View.setVisibilityToGone() {
    this.visibility = View.GONE
}

// Show Toast
fun Context.showShortToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}