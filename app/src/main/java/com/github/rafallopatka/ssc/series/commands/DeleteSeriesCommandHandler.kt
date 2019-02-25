package com.github.rafallopatka.ssc.series.commands

import com.github.rafallopatka.ssc.core.CommandHandler
import com.github.rafallopatka.ssc.db.UnitOfWork
import io.reactivex.Flowable

class DeleteSeriesCommandHandler(private val unitOfWork: UnitOfWork) :
    CommandHandler<DeleteSeriesCommand> {
    override fun process(command: DeleteSeriesCommand): Flowable<Unit> {
        return unitOfWork.transaction { db ->
            val dao = db.seriesDao()
            val entity = dao.getById(command.entity.id.toString())

            dao.deleteSeries(entity)
        }.map { Unit }
    }
}