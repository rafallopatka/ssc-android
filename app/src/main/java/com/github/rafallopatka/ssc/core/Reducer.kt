package com.github.rafallopatka.ssc.core

interface Reducer<TModelState : ModelState> {
    fun reduce(previousState: TModelState, reaction: Reaction): TModelState
}