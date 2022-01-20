package com.example.network_domain.network.interceptors

import android.annotation.SuppressLint
import com.example.network_domain.network.exceptions.NoInternetException
import com.example.network_domain.network.util.NetworkManager
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

@SuppressLint("MissingPermission")
class NoInternetInterceptor(private val networkManager: NetworkManager) : Interceptor {
  override fun intercept(chain: Chain): Response {
    return if (!networkManager.isInternetAvailable()) {
      throw NoInternetException()
    } else {
      chain.proceed(chain.request())
    }
  }
}