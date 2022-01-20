package com.example.toroi.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toroi.utils.toLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber

open class BaseViewModel() : ViewModel() {

  protected val _viewState = MutableLiveData<ViewState>(ViewState.Idle)
  val viewState = _viewState.toLiveData()

  fun launch(
    code: suspend CoroutineScope.() -> Unit
  ) {
    (viewModelScope + exceptionHandler).launch {
      code.invoke(this)
    }
  }

  private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
    handleFailure(throwable = exception)
  }

  private fun handleFailure(throwable: Throwable?) {
    Timber.e(throwable)
  }
}