package com.example.toroi.ui.home

import com.example.toroi.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    //  private val getAccountBalanceUseCase: GetAccountBalanceUseCase
) : BaseViewModel() {


/*
  fun getAccountBalance(centerId: String) {
    launch {
      _viewState.postValue(ViewState.Loading)
      when (val result = getAccountBalanceUseCase.perform(centerId)) {
        is Failure -> throw result.throwable
        is Success -> {
          _accountBalance.postValue(result.body)
          _viewState.postValue(ViewState.Success())
        }
      }
    }
  }
*/

}