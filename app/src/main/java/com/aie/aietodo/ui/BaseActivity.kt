package com.aie.aietodo.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.aie.aietodo.broadcast.NetworkChangeReceiver

/**
 * @class : BaseActivity
 * @desc : This class created to hold common across all activity
 * and all activity will extends this class
 * test commit
 * */
open class BaseActivity: AppCompatActivity()  {
    private lateinit var mNetworkReceiver: NetworkChangeReceiver
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //registering broadcast receiver
        mNetworkReceiver = NetworkChangeReceiver()
        registerNetworkBroadcastForNougat()
    }

    /**
     * @method : registerNetworkBroadcastForNougat
     * @desc : This method will register broadcaset receiver
     */
    private fun registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(
                mNetworkReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(
                mNetworkReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //un registering broadcast receiver
        unregisterNetworkChanges()
    }

    /**
     * @method : unregisterNetworkChanges
     * @desc : This method will un register broadcaset receiver
     */
    protected fun unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }
}