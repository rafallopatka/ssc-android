@file:Suppress("UNCHECKED_CAST")

package com.github.rafallopatka.ssc.core

abstract class ModelState {
    internal val state: MutableMap<String, PartialState> = mutableMapOf()

    internal abstract fun new(): ModelState
}

fun  <TModelState : ModelState> TModelState.snapshot(): TModelState {
    val newObject  = new()
    for (prop in state) {
        newObject.state[prop.key] = prop.value.snapshot()
    }

    return newObject as TModelState
}