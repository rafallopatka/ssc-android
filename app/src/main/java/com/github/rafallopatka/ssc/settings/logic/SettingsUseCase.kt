package com.github.rafallopatka.ssc.settings.logic

import com.github.rafallopatka.ssc.core.CommandBus
import com.github.rafallopatka.ssc.core.QueryBus
import com.github.rafallopatka.ssc.settings.commands.SaveSettingsCommand
import com.github.rafallopatka.ssc.settings.queries.GetSettingsQuery
import io.reactivex.Flowable

class SettingsUseCase(private val commandBus: CommandBus,
                      private val queryBus: QueryBus) {
    fun saveSettings(address: String) {
        commandBus.dispatch(SaveSettingsCommand(address))
    }

    fun loadSettings(): Flowable<SettingsDto> {
        return queryBus.query(GetSettingsQuery())
    }
}