package com.github.rafallopatka.ssc.core

import io.reactivex.Flowable

interface QueryBus {
    fun <TQuery: Query<TResult>, TResult: Any> query(query: TQuery): Flowable<TResult>
}