@file:Suppress("LeakingThis")

package com.github.rafallopatka.ssc.core

import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor

abstract class ReactiveModel<TModelState : ModelState> : ViewModel() {
    private val stream by lazy { PublishProcessor.create<Action>() }
    private val pipeline: Pipeline<TModelState> by lazy { createPipeline() }
    private val reducer: Reducer<TModelState> by lazy { createReducer() }

    private var accumulatorState = createEmptyState()
    private val result: Flowable<TModelState> by lazy {
        stream
            .concatMap { action ->
                val pipelineImmutableState = accumulatorState.snapshot()
                val reducerImmutableState = pipelineImmutableState.snapshot()
                pipeline
                    .run(pipelineImmutableState, action)
                    .map { reaction ->
                        reducer.reduce(reducerImmutableState, reaction)
                    }.doOnNext {
                        val newState = it.snapshot()
                        accumulatorState = newState
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .cache()
    }

    protected abstract fun createReducer(): Reducer<TModelState>
    protected abstract fun createPipeline(): Pipeline<TModelState>
    protected abstract fun createEmptyState(): TModelState

    fun react(): Flowable<TModelState> {
        return result
    }

    fun invoke(action: Action) {
        stream.onNext(action)
    }

    private var isNotAttached = true
    fun isNotAttached(): Boolean {
        return isNotAttached
    }

    fun attach() {
        isNotAttached = false
    }
}