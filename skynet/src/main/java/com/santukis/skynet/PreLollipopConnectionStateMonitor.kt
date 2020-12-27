package com.santukis.skynet

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager.CONNECTIVITY_ACTION

internal class PreLollipopConnectionStateMonitor(private val context: Context): ConnectionStateMonitor(context) {

    private val onConnectionChangeReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when(hasNetworkConnection()) {
                true -> onNetworkConnectionChange?.onAvailable()
                false -> onNetworkConnectionChange?.onLost()
            }
        }
    }

    override fun registerNetworkListener(onNetworkConnectionChange: OnNetworkConnectionChange?) {
        this.onNetworkConnectionChange = onNetworkConnectionChange
        context.registerReceiver(onConnectionChangeReceiver, IntentFilter(CONNECTIVITY_ACTION))
    }

    override fun unregisterNetworkListener() {
        context.unregisterReceiver(onConnectionChangeReceiver)
    }
}