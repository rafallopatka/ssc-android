package com.github.rafallopatka.ssc.dashboard.queries

import com.github.rafallopatka.ssc.core.QueryHandler
import com.github.rafallopatka.ssc.db.UnitOfWork
import com.github.rafallopatka.ssc.series.logic.SeriesDto
import io.reactivex.Flowable
import java.util.*

class SeriesQueryHandler(private val unitOfWork: UnitOfWork)
    : QueryHandler<SeriesQuery, Collection<SeriesDto>> {

    override fun handle(query: SeriesQuery): Flowable<Collection<SeriesDto>> {
        return unitOfWork.query { db ->
            val dao = db.seriesDao()
            dao.fetch(query.skip, query.take)
                .map {
                    SeriesDto(
                        UUID.fromString(it.seriesId!!),
                        it.name ?: "",
                        it.sequence ?: ""
                    )
                }
        }
    }
}