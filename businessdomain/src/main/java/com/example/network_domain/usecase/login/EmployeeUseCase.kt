package com.example.network_domain.usecase.login

import com.example.network_domain.network.model.EmployeeResponseModel
import com.example.network_domain.network.util.NetworkResult
import com.example.network_domain.network.util.NetworkResult.Failure
import com.example.network_domain.network.util.NetworkResult.Success
import com.example.network_domain.network.util.safeApiCall
import com.example.network_domain.repository.EmployeeRepository
import com.example.network_domain.usecase.base.BaseSuspendUseCase

class EmployeeUseCase(
    private val employeeRepository: EmployeeRepository
) : BaseSuspendUseCase<NetworkResult<EmployeeResponseModel>, String>() {
    override suspend fun perform(params: String): NetworkResult<EmployeeResponseModel> {
        return when (val result = safeApiCall { employeeRepository.getEmployee() }) {
            is Success -> Success(result.body)
            is Failure -> result
        }
    }

}