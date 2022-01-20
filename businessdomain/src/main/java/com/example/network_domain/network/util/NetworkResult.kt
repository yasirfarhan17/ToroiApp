package com.example.network_domain.network.util

sealed class NetworkResult<out T> {
  data class Success<out T>(val body: T) : NetworkResult<T>()
  data class Failure(val throwable: Throwable) : NetworkResult<Nothing>()

  fun result(): T {
    when (this) {
      is Success -> return body
      is Failure -> throw throwable
    }
  }

  fun resultIgnoreException(): T? {
    return when (this) {
      is Success -> body
      is Failure -> null
    }
  }
}