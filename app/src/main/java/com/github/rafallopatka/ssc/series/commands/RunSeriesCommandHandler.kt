package com.github.rafallopatka.ssc.series.commands

import android.util.Log
import com.github.rafallopatka.ssc.api.ApiFactory
import com.github.rafallopatka.ssc.core.CommandHandler
import com.github.rafallopatka.ssc.db.UnitOfWork
import io.reactivex.Flowable

class RunSeriesCommandHandler :
    CommandHandler<RunSeriesCommand> {
    override fun process(command: RunSeriesCommand): Flowable<Unit> {
        val api = ApiFactory.create()

        return api.runSequence(command.entity.sequence).doOnError {
            Log.d("api", "Run sequence error", it)
        }
    }
}