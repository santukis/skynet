package com.santukis.skynet

import android.content.Context
import android.net.ConnectivityManager

abstract class ConnectionStateMonitor(private val context: Context) {

    protected var onNetworkConnectionChange: OnNetworkConnectionChange? = null

    protected val connectivityManager: ConnectivityManager? by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    }


    fun hasNetworkConnection() = connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false

    abstract fun registerNetworkListener(onNetworkConnectionChange: OnNetworkConnectionChange? = null)

    abstract fun unregisterNetworkListener()

    interface OnNetworkConnectionChange {
        fun onAvailable()
        fun onLost()
    }
}