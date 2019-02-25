package com.github.rafallopatka.ssc.settings.logic

import com.github.rafallopatka.ssc.core.Reaction
import com.github.rafallopatka.ssc.core.Reducer
import com.github.rafallopatka.ssc.core.snapshot
import com.github.rafallopatka.ssc.settings.actions.ApiAddressChanged
import com.github.rafallopatka.ssc.settings.actions.SettingsInitialized

class SettingsStateReducer: Reducer<SettingsState> {
    override fun reduce(previousState: SettingsState, reaction: Reaction): SettingsState {
        val state = previousState.snapshot()

        if (reaction is SettingsInitialized){
            state.entity.set(reaction.settings)
            state.address.set(reaction.settings.address)
        }

        if (reaction is ApiAddressChanged){
            state.address.set(reaction.address)
        }

        return state
    }

}