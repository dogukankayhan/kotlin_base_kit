package com.base.domain.usecase

import com.base.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class FlowUseCase<in P, R>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    operator fun invoke(parameters: P): Flow<Result<R>> = execute(parameters)
        .map { Result.Success(it) as Result<R> }
        .catch { emit(Result.Error(it)) }
        .flowOn(dispatcher)

    protected abstract fun execute(parameters: P): Flow<R>
}

abstract class SuspendUseCase<in P, R>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            val result = execute(parameters)
            Result.Success(result)
        } catch (@Suppress("TooGenericExceptionCaught") e: Exception) {
            Result.Error(e)
        }
    }

    protected abstract suspend fun execute(parameters: P): R
}
