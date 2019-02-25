package com.github.rafallopatka.ssc.core

interface QueryHandlerRegistry{
    fun <TQuery: Query<TResult>, TResult: Any> get(query: TQuery): QueryHandler<TQuery, TResult>
}

