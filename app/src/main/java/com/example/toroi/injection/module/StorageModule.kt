package com.example.toroi.injection.module

import android.content.Context
import android.content.SharedPreferences
import com.example.network_domain.storage.PrefsUtil
import com.example.toroi.injection.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

  @Singleton
  @Provides
  fun provideSharedPreferences(
    @ApplicationContext context: Context
  ): SharedPreferences {
    return context.getSharedPreferences(
        PrefsUtil.SHARED_PREFERENCE_ID, Context.MODE_PRIVATE
    )
  }

  @Provides
  @Singleton
  fun providePrefUtils(sharedPreferences: SharedPreferences): PrefsUtil {
    return PrefsUtil(sharedPreferences)
  }

}