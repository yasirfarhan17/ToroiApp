package com.example.toroi.injection.module

import com.example.network_domain.network.api.EmployeeApi
import com.example.network_domain.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providePaymentRepository(
        api: EmployeeApi
    ): EmployeeRepository {
        return EmployeeRepository(api)
    }

}