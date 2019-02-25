package com.github.rafallopatka.ssc.series.logic

import com.github.rafallopatka.ssc.core.*
import com.github.rafallopatka.ssc.series.actions.*
import io.reactivex.Flowable
import java.util.*

class SeriesPipeline : Pipeline<SeriesState> {
    private val useCase by lazy { SeriesUseCase(ServiceResolver.get(), ServiceResolver.get()) }

    override fun run(immutableState: SeriesState, action: Action): Flowable<Reaction> {
        return when (action) {
            is SeriesInitialize -> {
                handleInitialize(action)
            }
            is Restore -> {
                restored(action)
            }
            is ChangeSequence -> {
                handleSequenceChanges(action)
            }
            is ChangeName -> {
                handleNameChanges(action)
            }
            is Confirm -> {
                handleConfirm(action, immutableState)
            }
            else -> {
                noop(action)
            }
        }
    }

    private fun handleInitialize(action: SeriesInitialize): Flowable<Reaction> {
        return when (action.sequenceInvokeArg) {
            is SeriesInvokeArg.Create -> {
                Flowable.just(SeriesDto(UUID.randomUUID(), "", ""))
                    .map { SeriesInitialized(action, it, action.sequenceInvokeArg) }
            }
            is SeriesInvokeArg.Edit -> {
                useCase.get(action.sequenceInvokeArg.id)
                    .map { SeriesInitialized(action, it, action.sequenceInvokeArg) }
            }
            is SeriesInvokeArg.Run -> {
                useCase.get(action.sequenceInvokeArg.id)
                    .map { SeriesInitialized(action, it, action.sequenceInvokeArg) }
            }
            is SeriesInvokeArg.Delete -> {
                useCase.get(action.sequenceInvokeArg.id)
                    .map { SeriesInitialized(action, it, action.sequenceInvokeArg) }
            }
        }
    }

    private fun handleSequenceChanges(action: ChangeSequence): Flowable<Reaction> {
        return Flowable.just(action)
            .map { SequenceChanged(action, it.text) }
    }

    private fun handleNameChanges(action: ChangeName): Flowable<Reaction> {
        return Flowable.just(action)
            .map { NameChanged(action, it.text) }
    }

    private fun handleConfirm(
        action: Confirm,
        state: SeriesState
    ): Flowable<Reaction> {

        return Flowable.just(action)
            .switchMap { useCase.run(action, state.entity.get()) }
            .map { Confirmed(action) }
    }
}

