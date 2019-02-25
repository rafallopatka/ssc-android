package com.github.rafallopatka.ssc.series.commands

import com.github.rafallopatka.ssc.core.CommandHandler
import com.github.rafallopatka.ssc.db.SeriesEntity
import com.github.rafallopatka.ssc.db.UnitOfWork
import io.reactivex.Flowable

class CreateNewSeriesCommandHandler(private val unitOfWork: UnitOfWork) :
    CommandHandler<CreateNewSeriesCommand> {

    override fun process(command: CreateNewSeriesCommand): Flowable<Unit> {
        return unitOfWork.transaction { db ->
            val dao = db.seriesDao()
            val entity = SeriesEntity().apply {
                this.name = command.series.name
                this.sequence = command.series.sequence
                this.seriesId = command.series.id.toString()
            }

            dao.insertSingle(entity)
        }.map { Unit }
    }
}

