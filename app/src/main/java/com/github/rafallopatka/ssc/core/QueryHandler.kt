package com.github.rafallopatka.ssc.core

import io.reactivex.Flowable

interface QueryHandler<TQuery: Query<TResult>, TResult: Any>{
    fun handle(query: TQuery): Flowable<TResult>
}