package com.mijasmarttasks.common

import android.net.ConnectivityManager
import android.net.Network

class NetworkConnectionCallback(
    private val onConnectionLost: () -> Unit,
    private val onConnectionRestored: () -> Unit
) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        onConnectionRestored()
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        onConnectionLost()
    }
}