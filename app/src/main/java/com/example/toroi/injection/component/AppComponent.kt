package com.example.toroi.injection.component

import android.content.Context
import com.example.toroi.injection.module.NetworkModule
import com.example.toroi.injection.module.RepositoryModule
import com.example.toroi.injection.module.StorageModule
import com.example.toroi.injection.module.UseCaseModule
import com.example.toroi.injection.module.ViewModelFactoryModule
import com.example.toroi.injection.qualifiers.ApplicationContext
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class, UseCaseModule::class,
      ViewModelFactoryModule::class, AppSubComponents::class,
      StorageModule::class, NetworkModule::class]
)
interface AppComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance @ApplicationContext context: Context): AppComponent
  }

  fun homeComponent(): HomeComponent.Factory

}