package com.example.network_domain.network.api

import com.example.network_domain.network.model.EmployeeResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {
    @GET(Endpoints.GET_EMPLOYEE)
    suspend fun getEmployee(): Response<EmployeeResponseModel>

}