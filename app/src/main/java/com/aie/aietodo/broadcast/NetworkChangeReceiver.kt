package com.aie.aietodo.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

/**
 * @class : NetworkChangeReceiver
 * @desc : This class created to listen Internet connectivity
 */
class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            if (isNetworkAvailable(context)) {
                Toast.makeText(context, "Network Available!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Network is not Available!!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }
    /**
     * @method : isNetworkAvailable
     * @desc : This method will check network availablity
     * Dispatchers.Main for main safety
     * @param : application context
     * @return : boolean
     */
    fun isNetworkAvailable(context: Context?): Boolean {
        val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connMgr != null) {
            val activeNetworkInfo = connMgr.activeNetworkInfo
            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                return if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    true
                } else
                    activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
            }
        }
        return false
    }
}