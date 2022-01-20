package com.example.network_domain.network.util

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import timber.log.Timber

@SuppressLint("MissingPermission")
class NetworkManager(private val connectivityManager: ConnectivityManager) {

  private val listeners by lazy { hashSetOf<NetworkListener>() }
  private var networkCallbackListener: NetworkCallbackListener? = null

  fun isInternetAvailable(): Boolean {
    return isNetworkConnected()
  }

  /**
   * call this method when min sdk is 23 or less
   */
  @Suppress("DEPRECATION")
  private fun isNetworkConnectedDeprecated(): Boolean {
    var isConnected = false // Initial Value
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
      isConnected = true
    return isConnected
  }

  @RequiresApi(VERSION_CODES.M)
  private fun isNetworkConnected(): Boolean {
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    capabilities.also {
      if (it != null) {
        if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
          return true
        else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
          return true
        }
      }
    }
    return false
  }

  @SuppressLint("MissingPermission")
  fun registerNetworkListener(networkListener: NetworkListener) {
    listeners.add(networkListener)
    Timber.d("Network listener $networkListener registered")
    synchronized(this) {

      if (networkCallbackListener == null) {
        networkCallbackListener = NetworkCallbackListener()
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder()
                .build(), networkCallbackListener!!
        )
        networkCallbackListener
      }
    }
  }

  fun unregisterNetworkListener(networkListener: NetworkListener) {
    if (listeners.contains(networkListener)) {
      listeners.remove(networkListener)
      Timber.d("Network listener $networkListener removed")
    }
  }

  inner class NetworkCallbackListener : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
      super.onAvailable(network)
      synchronized(this@NetworkManager) {
        if (isInternetAvailable()) {
          Timber.d("Network onAvailable $network")
          listeners.forEach { it.onAvailability(isInternetAvailable(), network) }
        }
      }
    }

    override fun onLost(network: Network) {
      super.onLost(network)
      synchronized(this@NetworkManager) {
        if (!isInternetAvailable()) {
          Timber.d("Network onLost $network")
          listeners.forEach { it.onAvailability(!isInternetAvailable(), network) }
        }
      }
    }
  }

  interface NetworkListener {
    fun onAvailability(
      isAvailable: Boolean,
      network: Network?
    )
  }
}