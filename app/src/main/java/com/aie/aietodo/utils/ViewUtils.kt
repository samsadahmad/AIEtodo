package net.simplifiedcoding.mvvmsampleapp.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * @method : toast
 * @desc : this extension method created for showing toast message
 * @param: String (Message)
 */
fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG ).show()
}

/**
 * @method : toast
 * @desc : this extension method created for hiding view
 * @param: String (Message)
 */
fun View.hide(){
    visibility = View.GONE
}

/**
 * @method : toast
 * @desc : this extension method created for show view
 * @param: String (Message)
 */
fun View.show(){
    visibility = View.VISIBLE
}

