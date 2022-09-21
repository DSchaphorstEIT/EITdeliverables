package com.eitcat.dschaphorst_p4_movies.util

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Used to track the connectivity state and control the ConnectivityManager
 *
 * @property connectivityManager Object used to check for internet connection.
 */
class ConnectivityState(
    private val connectivityManager: ConnectivityManager
) {

    /**
     * Check the internet connection
     *
     * @return True if connected to internet; otherwise, false.
     */
    fun checkInternetConnection(): Boolean {
        return connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }
}