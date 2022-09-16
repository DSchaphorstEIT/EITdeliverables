package com.eitcat.dschaphorst_p3_music.util

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectivityState(
    private val connectivityManager: ConnectivityManager
) {

    fun checkInternetConnection(): Boolean {
        return connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }
}