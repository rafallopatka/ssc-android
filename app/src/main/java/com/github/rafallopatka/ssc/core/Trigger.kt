package com.github.rafallopatka.ssc.core

import org.joda.time.DateTime

data class Trigger<TValue>(override var lastModification: DateTime = DateTime.now()) :
    PartialState {
    private var invocation = Invocation<TValue>()

    fun shouldBeHandled(): Boolean {
        return invocation.shouldBeHandled
    }

    override fun markAsUpdated() {
        lastModification = DateTime.now()
    }

    fun invoke(parameter: TValue?) {
        this.lastModification = DateTime.now()
        this.invocation.parameter = parameter
        this.invocation.shouldBeHandled = true
    }

    override fun snapshot(): PartialState {
        val copy = this.copy()
        copy.invocation = this.invocation
        return copy
    }

    fun handle() {
        this.invocation.shouldBeHandled = false
    }
}