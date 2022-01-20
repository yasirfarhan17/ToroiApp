package com.example.network_domain.network

import android.content.Context
import com.example.network_domain.network.interceptors.HeaderInterceptor
import com.example.network_domain.network.interceptors.NoInternetInterceptor
import com.example.network_domain.network.util.NetworkManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit.SECONDS

object NetworkClient {

    private const val TIMEOUT_IN_SECONDS = 20L
    private const val CONTENT_TYPE_APPLICATION = "application/json"

    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .addConverterFactory(getJson().asConverterFactory(CONTENT_TYPE_APPLICATION.toMediaType()))
        .client(okHttpClient)
        .build()

    private fun getJson() = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    fun provideOkHttp(
        context: Context,
        headerInterceptor: HeaderInterceptor,
        networkManager: NetworkManager
    ): OkHttpClient {

        val httpBuilder = OkHttpClient.Builder()


        httpBuilder.apply {
            // addInterceptor(ChuckerInterceptor(context))
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        httpBuilder.apply {
            addInterceptor(NoInternetInterceptor(networkManager))
            addInterceptor(headerInterceptor)
            connectTimeout(TIMEOUT_IN_SECONDS, SECONDS)
            writeTimeout(TIMEOUT_IN_SECONDS, SECONDS)
            readTimeout(TIMEOUT_IN_SECONDS, SECONDS)
        }
        return httpBuilder.build()
    }
}