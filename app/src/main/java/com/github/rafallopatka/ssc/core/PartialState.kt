package com.github.rafallopatka.ssc.core

import org.joda.time.DateTime

interface PartialState {
    val lastModification: DateTime
    fun snapshot(): PartialState
    fun markAsUpdated()
}