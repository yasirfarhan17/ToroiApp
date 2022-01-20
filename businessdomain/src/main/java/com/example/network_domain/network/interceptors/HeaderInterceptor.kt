package com.example.network_domain.network.interceptors

import com.example.network_domain.storage.PrefsUtil
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class HeaderInterceptor(private val prefsUtil: PrefsUtil) : Interceptor {

  override fun intercept(chain: Chain): Response {

    return prefsUtil.accessToken?.let {
      chain.proceed(
          chain.request()
              .newBuilder()
              .header("Authorization", "Bearer $it")
              .build()
      )
    } ?: kotlin.run { chain.proceed(chain.request()) }
  }
}