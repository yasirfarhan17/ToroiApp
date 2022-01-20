package com.example.toroi.injection.module

import com.example.network_domain.repository.EmployeeRepository
import com.example.network_domain.usecase.login.EmployeeUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideBeforePaymentUseCase(
        repo: EmployeeRepository
    ): EmployeeUseCase {
        return EmployeeUseCase(repo)
    }


}