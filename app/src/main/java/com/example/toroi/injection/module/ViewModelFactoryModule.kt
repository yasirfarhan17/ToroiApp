package com.example.toroi.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.toroi.injection.scope.ViewModelScope
import com.example.toroi.ui.details_view.DetailViewModel
import com.example.toroi.ui.home.HomeViewModel
import com.example.toroi.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelScope(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(DetailViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailViewModel): ViewModel

}