package com.santukis.skynet

import android.content.Context
import android.os.Build

class ConnectionStateMonitorFactory(private val context: Context) {

    fun build(): ConnectionStateMonitor =
        when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> PostNougatConnectionStateMonitor(context)
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> PostLollipopConnectionStateMonitor(context)
                else -> PreLollipopConnectionStateMonitor(context)

        }
}