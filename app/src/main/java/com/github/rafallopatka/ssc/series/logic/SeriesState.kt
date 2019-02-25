package com.github.rafallopatka.ssc.series.logic

import com.github.rafallopatka.ssc.core.ModelState
import com.github.rafallopatka.ssc.core.Trigger
import com.github.rafallopatka.ssc.core.Value
import com.github.rafallopatka.ssc.series.presentation.ViewMode

class SeriesState: ModelState() {
    override fun new(): ModelState {
        return SeriesState()
    }

    var entity: Value<SeriesDto> by state
    var name: Value<String> by state
    var sequence: Value<String> by state
    var mode: Value<ViewMode> by state
    var lockInputs: Value<Boolean> by state

    var leave: Trigger<Unit> by state

    init {
        entity = Value()
        name = Value()
        sequence = Value()
        mode = Value()
        lockInputs = Value(false)

        leave = Trigger()
    }
}