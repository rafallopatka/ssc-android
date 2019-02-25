package com.github.rafallopatka.ssc.settings.presentation

import com.github.rafallopatka.ssc.core.Pipeline
import com.github.rafallopatka.ssc.core.ReactiveModel
import com.github.rafallopatka.ssc.core.Reducer
import com.github.rafallopatka.ssc.settings.logic.SettingsPipeline
import com.github.rafallopatka.ssc.settings.logic.SettingsState
import com.github.rafallopatka.ssc.settings.logic.SettingsStateReducer

class SettingsModel: ReactiveModel<SettingsState>(){
    override fun createReducer(): Reducer<SettingsState> {
        return SettingsStateReducer()
    }

    override fun createPipeline(): Pipeline<SettingsState> {
        return SettingsPipeline()
    }

    override fun createEmptyState(): SettingsState {
        return SettingsState()
    }
}