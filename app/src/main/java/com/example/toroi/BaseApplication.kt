package com.example.toroi

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber
import timber.log.Timber.DebugTree
import com.example.toroi.injection.component.AppComponent
import com.example.toroi.injection.component.DaggerAppComponent

class BaseApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    plantTimber()
    startStetho()
  }

  private fun plantTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }

  private fun startStetho() {
    if (BuildConfig.DEBUG) {
      Stetho.initializeWithDefaults(this)
    }
  }

  val appComponent: AppComponent by lazy {
    DaggerAppComponent.factory()
        .create(applicationContext)
  }
}
//TODO: replace google-services.json with original