package com.example.toroi.injection.module

import android.content.Context
import android.net.ConnectivityManager
import com.example.network_domain.network.NetworkClient
import com.example.network_domain.network.api.EmployeeApi
import com.example.network_domain.network.interceptors.HeaderInterceptor
import com.example.network_domain.network.util.NetworkManager
import com.example.network_domain.storage.PrefsUtil
import com.example.toroi.injection.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkManager(
        @ApplicationContext context: Context
    ): NetworkManager {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return NetworkManager(connectivityManager)
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(
        prefsUtil: PrefsUtil
    ): HeaderInterceptor = HeaderInterceptor(prefsUtil)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        headerInterceptor: HeaderInterceptor,
        networkManager: NetworkManager
    ): OkHttpClient {
        return NetworkClient.provideOkHttp(
            context,
            headerInterceptor,
            networkManager
        )
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = NetworkClient.provideRetrofit(okHttpClient)

    @Singleton
    @Provides
    fun providePaymentApiService(
        retrofit: Retrofit
    ): EmployeeApi = retrofit.create(EmployeeApi::class.java)


}