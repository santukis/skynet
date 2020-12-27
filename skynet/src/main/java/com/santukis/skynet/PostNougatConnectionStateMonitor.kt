package com.santukis.skynet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
internal class PostNougatConnectionStateMonitor(private val context: Context): ConnectionStateMonitor(context) {

    private val networkCallback = object: ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network) {
            onNetworkConnectionChange?.onLost()
        }

        override fun onAvailable(network: Network) {
            onNetworkConnectionChange?.onAvailable()
        }
    }

    override fun registerNetworkListener(onNetworkConnectionChange: OnNetworkConnectionChange?) {
        this.onNetworkConnectionChange = onNetworkConnectionChange
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)
    }


    override fun unregisterNetworkListener() {
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }
}