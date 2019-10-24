package com.example.foodtruck.util

import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

//create an alert with two buttons
fun Context.createAlert(
    positiveListener: (DialogInterface, Int) -> Unit,
    negativeListener: (DialogInterface, Int) -> Unit,
    positiveButtonText: String,
    negativeButtonText: String,
    message: String? = null,
    view: View? = null
) : AlertDialog {
    val a = AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(positiveButtonText, positiveListener)
        .setNegativeButton(negativeButtonText, negativeListener)

    view?.let{
        a.setView(it)
    }
    message?.let{
        a.setMessage(it)
    }
    return a.create()
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