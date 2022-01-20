package com.example.network_domain.usecase.base

abstract class BaseSuspendUseCase<out Result, in Request> {
  abstract suspend fun perform(params: Request): Result
}