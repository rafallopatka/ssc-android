package com.github.rafallopatka.ssc.settings.logic

import com.github.rafallopatka.ssc.core.*
import com.github.rafallopatka.ssc.settings.actions.ApiAddressChanged
import com.github.rafallopatka.ssc.settings.actions.ChangeApiAddress
import com.github.rafallopatka.ssc.settings.actions.SettingsInitialized
import io.reactivex.Flowable

class SettingsPipeline : Pipeline<SettingsState> {
    private val useCase = SettingsUseCase(ServiceResolver.get(), ServiceResolver.get())
    override fun run(immutableState: SettingsState, action: Action): Flowable<Reaction> {
        return when (action) {
            is Initialize -> {
                handleInitialize(action)
            }
            is Restore -> {
                restored(action)
            }
            is ChangeApiAddress -> {
                handleChangeAddress(action, immutableState)
            }
            else -> {
                noop(action)
            }
        }
    }

    private fun handleInitialize(action: Initialize): Flowable<Reaction> {
        return useCase.loadSettings()
            .map { SettingsInitialized(action, settings = it) }
    }


    private fun handleChangeAddress(
        action: ChangeApiAddress,
        state: SettingsState
    ): Flowable<Reaction> {

        return Flowable.just(action)
            .doOnNext {
                useCase.saveSettings(it.address)
            }.map { ApiAddressChanged(action, it.address) }
    }
}