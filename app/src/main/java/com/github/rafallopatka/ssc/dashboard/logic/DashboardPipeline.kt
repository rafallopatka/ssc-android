package com.github.rafallopatka.ssc.dashboard.logic

import com.github.rafallopatka.ssc.core.*
import com.github.rafallopatka.ssc.dashboard.queries.SeriesQuery
import com.github.rafallopatka.ssc.series.logic.SeriesDto
import io.reactivex.Flowable
import java.util.*

class DashboardPipeline(private val queryBus: QueryBus)
    : Pipeline<DashboardState> {

    override fun run(immutableState: DashboardState, action: Action): Flowable<Reaction> {
        if (action is Initialize){
            return loadList(action)
        }

        if (action is Restore){
            return loadList(action)
        }

        return Flowable.just(Noop(action))
    }

    private fun loadList(action: Action): Flowable<Reaction>{
        val query = SeriesQuery(0, 1000000) // no paging now :(
        return queryBus.query(query)
            .map {
                val collection = it.map { Series(it.id, it.name, it.sequence) }
                ListUpdated(action, collection)
            }
    }
}

