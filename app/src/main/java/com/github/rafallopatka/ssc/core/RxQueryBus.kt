package com.github.rafallopatka.ssc.core

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class RxQueryBus: QueryBus {
    override fun <TQuery: Query<TResult>, TResult: Any> query(query: TQuery): Flowable<TResult> {
        return QueryHandlerResolver.registry
            .get(query)
            .handle(query)
            .subscribeOn(Schedulers.io())
    }
}