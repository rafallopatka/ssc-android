package com.github.rafallopatka.ssc

import com.github.rafallopatka.ssc.core.CommandBus
import com.github.rafallopatka.ssc.core.ServiceResolver
import com.github.rafallopatka.ssc.series.commands.*
import com.github.rafallopatka.ssc.settings.commands.SaveSettingsCommand
import com.github.rafallopatka.ssc.settings.commands.SaveSettingsCommandHandler

object CommandBindings {
    private val commandBus by lazy { ServiceResolver.get<CommandBus>() }

    @Suppress("UNCHECKED_CAST")
    fun init() {
        commandBus.bind(
            CreateNewSeriesCommand::class, CreateNewSeriesCommandHandler(inject())
        )
        commandBus.bind(
            UpdateSeriesCommand::class, UpdateSeriesCommandHandler(inject())
        )
        commandBus.bind(
            DeleteSeriesCommand::class, DeleteSeriesCommandHandler(inject())
        )
        commandBus.bind(
            RunSeriesCommand::class, RunSeriesCommandHandler()
        )
        commandBus.bind(
            SaveSettingsCommand::class, SaveSettingsCommandHandler(inject())
        )
    }

    inline fun <reified TType : Any> inject(): TType {
        return ServiceResolver.get()
    }
}