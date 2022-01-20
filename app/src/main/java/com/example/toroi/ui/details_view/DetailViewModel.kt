package com.example.toroi.ui.details_view

import androidx.lifecycle.MutableLiveData
import com.example.network_domain.network.model.EmployeeResponseModel
import com.example.network_domain.network.util.NetworkResult
import com.example.network_domain.usecase.login.EmployeeUseCase
import com.example.toroi.ui.base.BaseViewModel
import com.example.toroi.ui.base.ViewState
import com.example.toroi.utils.toLiveData
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getEmployeeUseCase: EmployeeUseCase
) : BaseViewModel() {


    private val _employeeList = MutableLiveData<EmployeeResponseModel?>()
    val employeeList = _employeeList.toLiveData()


    fun getEmployeeList() {
        launch {
            _viewState.postValue(ViewState.Loading)
            when (val result = getEmployeeUseCase.perform("")) {
                is NetworkResult.Failure -> throw result.throwable
                is NetworkResult.Success -> {
                    _employeeList.postValue(result.body)
                    _viewState.postValue(ViewState.Success())
                }
            }
        }
    }


}