package com.santukis.skynet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
internal class PostLollipopConnectionStateMonitor(private val context: Context) :
    ConnectionStateMonitor(context) {

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network) {
            onNetworkConnectionChange?.onLost()
        }

        override fun onAvailable(network: Network) {
            onNetworkConnectionChange?.onAvailable()
        }
    }

    override fun registerNetworkListener(onNetworkConnectionChange: OnNetworkConnectionChange?) {
        this.onNetworkConnectionChange = onNetworkConnectionChange
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build(), networkCallback
        )
    }


    override fun unregisterNetworkListener() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}