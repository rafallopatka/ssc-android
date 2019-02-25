package com.github.rafallopatka.ssc.series.commands

import com.github.rafallopatka.ssc.core.CommandHandler
import com.github.rafallopatka.ssc.db.UnitOfWork
import io.reactivex.Flowable

class UpdateSeriesCommandHandler(private val unitOfWork: UnitOfWork) :
    CommandHandler<UpdateSeriesCommand> {

    override fun process(command: UpdateSeriesCommand): Flowable<Unit> {
        return unitOfWork.transaction { db ->
            val dao = db.seriesDao()
            val entity = dao.getById(command.entity.id.toString())
            entity.name = command.entity.name
            entity.sequence = command.entity.sequence
            dao.updateSeries(entity)
        }.map { Unit }
    }
}