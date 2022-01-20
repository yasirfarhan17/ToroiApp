package com.example.network_domain.repository

import com.example.network_domain.network.api.EmployeeApi

class EmployeeRepository(
    private val employeeApi: EmployeeApi
) {
    suspend fun getEmployee() = employeeApi.getEmployee()

}