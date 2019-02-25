package com.github.rafallopatka.ssc.core

import org.joda.time.DateTime

data class Value<TValue>(
    private var value: TValue? = null,
    override var lastModification: DateTime = DateTime.now()
) : PartialState {

    fun set(value: TValue) {
        this.value = value
        this.lastModification = DateTime.now()
    }

    fun get(): TValue {
        return value!!
    }

    override fun markAsUpdated() {
        lastModification = DateTime.now()
    }

    override fun snapshot(): PartialState {
        return this.copy()
    }
}