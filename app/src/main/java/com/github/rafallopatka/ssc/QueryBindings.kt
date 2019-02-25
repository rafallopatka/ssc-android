@file:Suppress("CAST_NEVER_SUCCEEDS", "UNCHECKED_CAST")

package com.github.rafallopatka.ssc

import com.github.rafallopatka.ssc.core.Query
import com.github.rafallopatka.ssc.core.QueryHandler
import com.github.rafallopatka.ssc.core.QueryHandlerRegistry
import com.github.rafallopatka.ssc.core.ServiceResolver
import com.github.rafallopatka.ssc.dashboard.queries.SeriesQuery
import com.github.rafallopatka.ssc.dashboard.queries.SeriesQueryHandler
import com.github.rafallopatka.ssc.series.queries.GetSeriesByIdQuery
import com.github.rafallopatka.ssc.series.queries.GetSeriesByIdQueryHandler
import com.github.rafallopatka.ssc.settings.queries.GetSettingsQuery
import com.github.rafallopatka.ssc.settings.queries.GetSettingsQueryHandler
import java.lang.IllegalStateException

object QueryBindings: QueryHandlerRegistry{
    override fun <TQuery: Query<TResult>, TResult: Any> get(query: TQuery)
            : QueryHandler<TQuery, TResult> {

        return when(query){
            is SeriesQuery -> SeriesQueryHandler(ServiceResolver.get()) as QueryHandler<TQuery, TResult>
            is GetSeriesByIdQuery -> GetSeriesByIdQueryHandler(ServiceResolver.get()) as QueryHandler<TQuery, TResult>
            is GetSettingsQuery -> GetSettingsQueryHandler(ServiceResolver.get()) as QueryHandler<TQuery, TResult>
            else -> throw IllegalStateException("No query handler for query $query")
        }
    }
}