package com.github.rafallopatka.ssc.core

import io.reactivex.Flowable

interface Pipeline<TModelState: ModelState> {
    fun run(immutableState: TModelState, action: Action): Flowable<Reaction>
}

@Suppress("unused")
fun <TModelState: ModelState> Pipeline<TModelState>.noop(action: Action): Flowable<Reaction> {
    return Flowable.just(Noop(action))
}

@Suppress("unused")
fun <TModelState: ModelState> Pipeline<TModelState>.restored(action: Action): Flowable<Reaction> {
    return Flowable.just(Restored(action))
}