package com.github.rafallopatka.ssc.series.queries

import com.github.rafallopatka.ssc.core.QueryHandler
import com.github.rafallopatka.ssc.db.UnitOfWork
import com.github.rafallopatka.ssc.series.logic.SeriesDto
import io.reactivex.Flowable
import java.util.*

class GetSeriesByIdQueryHandler(private val unitOfWork: UnitOfWork)
    : QueryHandler<GetSeriesByIdQuery, SeriesDto> {
    override fun handle(query: GetSeriesByIdQuery): Flowable<SeriesDto> {
        return unitOfWork.query { db ->
            val dao = db.seriesDao()
            dao.getById(query.id.toString())
        }.map {
            SeriesDto(
                UUID.fromString(it.seriesId),
                it.name ?: "",
                it.sequence ?: ""
            )
        }
    }
}