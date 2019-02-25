package com.github.rafallopatka.ssc.dashboard.logic

import com.github.rafallopatka.ssc.core.Reaction
import com.github.rafallopatka.ssc.core.Reducer
import com.github.rafallopatka.ssc.core.Restored
import com.github.rafallopatka.ssc.core.snapshot

class DashboardReducer : Reducer<DashboardState> {
    override fun reduce(previousState: DashboardState, reaction: Reaction): DashboardState {
        val state = previousState.snapshot()

        if (reaction is Restored) {
            state.state.forEach { it.value.markAsUpdated() }
        }

        if (reaction is ListUpdated) {
            state.data.set(reaction.list)
        }

        return state
    }
}