package com.github.rafallopatka.ssc.series.logic

import com.github.rafallopatka.ssc.core.Action
import com.github.rafallopatka.ssc.core.CommandBus
import com.github.rafallopatka.ssc.core.QueryBus
import com.github.rafallopatka.ssc.series.actions.Confirm
import com.github.rafallopatka.ssc.series.commands.CreateNewSeriesCommand
import com.github.rafallopatka.ssc.series.commands.DeleteSeriesCommand
import com.github.rafallopatka.ssc.series.commands.RunSeriesCommand
import com.github.rafallopatka.ssc.series.commands.UpdateSeriesCommand
import com.github.rafallopatka.ssc.series.queries.GetSeriesByIdQuery
import io.reactivex.Flowable
import java.util.*

class SeriesUseCase(private val commandBus: CommandBus,
                    private val queryBus: QueryBus) {
    fun run(action: Confirm, entity: SeriesDto): Flowable<Action> {
        return Flowable.just(Pair(action, entity))
            .map { (action, entity) ->
                return@map when (action.arg) {
                    is SeriesInvokeArg.Create -> {
                        createNewEntity(entity)
                    }
                    is SeriesInvokeArg.Edit -> {
                        editEntity(entity)
                    }
                    is SeriesInvokeArg.Delete -> {
                        deleteEntity(entity)
                    }
                    is SeriesInvokeArg.Run -> {
                        runSeries(entity)
                    }
                }
            }.map { action }
    }

    private fun createNewEntity(entity: SeriesDto) {
        commandBus.dispatch(CreateNewSeriesCommand(entity))
    }

    private fun deleteEntity(entity: SeriesDto) {
        commandBus.dispatch(DeleteSeriesCommand(entity))
    }

    private fun editEntity(entity: SeriesDto) {
        commandBus.dispatch(UpdateSeriesCommand(entity))
    }

    private fun runSeries(entity: SeriesDto) {
        commandBus.dispatch(RunSeriesCommand(entity))
    }

    fun get(id: UUID): Flowable<SeriesDto> {
        return queryBus.query(GetSeriesByIdQuery(id))
    }
}

