package com.github.rafallopatka.ssc.dashboard.logic

import com.github.rafallopatka.ssc.core.ModelState
import com.github.rafallopatka.ssc.core.Value

class DashboardState : ModelState() {
    override fun new(): ModelState {
        return DashboardState()
    }

    var initialized: Value<Boolean> by state

    var data: Value<Collection<Series>> by state

    init {
        initialized = Value(false)

        data = Value(emptyList())
    }
}