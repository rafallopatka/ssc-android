package com.github.rafallopatka.ssc.db

sealed class TransactionResult<TResult>{
    data class Success<TResult>(val result: TResult): TransactionResult<TResult>()
    data class Failure<TResult>(val throwable: Throwable): TransactionResult<TResult>()
}