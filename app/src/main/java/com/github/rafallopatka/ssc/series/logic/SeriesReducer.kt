package com.github.rafallopatka.ssc.series.logic

import com.github.rafallopatka.ssc.core.Reaction
import com.github.rafallopatka.ssc.core.Reducer
import com.github.rafallopatka.ssc.core.Restored
import com.github.rafallopatka.ssc.core.snapshot
import com.github.rafallopatka.ssc.series.actions.Confirmed
import com.github.rafallopatka.ssc.series.actions.NameChanged
import com.github.rafallopatka.ssc.series.actions.SequenceChanged
import com.github.rafallopatka.ssc.series.actions.SeriesInitialized
import com.github.rafallopatka.ssc.series.presentation.ViewMode

class SeriesReducer: Reducer<SeriesState> {
    override fun reduce(previousState: SeriesState, reaction: Reaction): SeriesState {
        val currentState = previousState.snapshot()

        if (reaction is SeriesInitialized){
            val entity = reaction.entity
            currentState.entity.set(entity)

            currentState.name.set(entity.name)
            currentState.sequence.set(entity.sequence)

            when(reaction.invokeArg){
                is SeriesInvokeArg.Create -> {
                    currentState.mode.set(ViewMode.Create)
                }
                is SeriesInvokeArg.Edit -> {
                    currentState.mode.set(ViewMode.Edit)
                }
                is SeriesInvokeArg.Run -> {
                    currentState.mode.set(ViewMode.View)
                }
                is SeriesInvokeArg.Delete -> {
                    currentState.mode.set(ViewMode.Delete)
                    currentState.lockInputs.set(true)
                }
            }
        }

        if (reaction is SequenceChanged){
            val entity = currentState.entity.get().copy(
                sequence = reaction.sequence
            )
            currentState.entity.set(entity)
        }

        if (reaction is NameChanged){
            val entity = currentState.entity.get().copy(
                name = reaction.name
            )
            currentState.entity.set(entity)
        }

        if (reaction is Restored){
            val entity = currentState.entity.get()
            currentState.name.set(entity.name)
            currentState.sequence.set(entity.sequence)
        }

        if (reaction is Confirmed){
            currentState.leave.invoke(Unit)
        }

        return currentState
    }

}

