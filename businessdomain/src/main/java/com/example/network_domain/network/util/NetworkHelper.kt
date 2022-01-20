package com.example.network_domain.network.util

import com.example.network_domain.network.exceptions.ApiException
import com.example.network_domain.network.exceptions.UnauthorizedException
import com.example.network_domain.network.util.NetworkResult.Failure
import com.example.network_domain.network.util.NetworkResult.Success
import com.example.network_domain.util.io
import okhttp3.ResponseBody
import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
  return io {
    try {
      apiCall.invoke()
          .resolveResponse()
    } catch (e: Exception) {
      Failure(e)
    }
  }
}

private fun <T> Response<T>.resolveResponse(): NetworkResult<T> {
  return if (this.isSuccessful) {
    this.body()
        ?.let {
          Success(it)
        } ?: kotlin.run {
      Failure(
          this.errorBody()
              .toApiError()
      )
    }
  } else {
    handleFailure()
  }
}

suspend fun <T> safeApiCallHeaders(apiCall: suspend () -> Response<T>): NetworkResult<Pair<T, String>> {
  return io {
    try {
      apiCall.invoke()
          .resolveResponseHeaders()
    } catch (e: Exception) {
      Failure(e)
    }
  }
}



private fun <T> Response<T>.resolveResponseHeaders(): NetworkResult<Pair<T, String>> {
  if (this.isSuccessful) {
    if (this.body() == null || this.headers()["token"].isNullOrBlank())
      return kotlin.run { Failure(this.errorBody().toApiError()) }
    return Success(Pair(this.body()!!, this.headers()["token"]!!))
  } else {
    return handleFailure()
  }
}

private fun <T> Response<T>.handleFailure(): Failure {
  if (code() == 403) return Failure(UnauthorizedException())
  return Failure(
      this.errorBody()
          .toApiError()
  )
}
private fun ResponseBody?.toApiError(): ApiException {
  return ApiException()
}