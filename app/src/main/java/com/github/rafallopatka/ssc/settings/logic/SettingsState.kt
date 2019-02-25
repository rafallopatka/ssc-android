package com.github.rafallopatka.ssc.settings.logic

import com.github.rafallopatka.ssc.core.*

class SettingsState: ModelState(){
    var entity: Value<SettingsDto> by state
    var address: Value<String> by state

    init {
        address = Value()
        entity = Value()
    }

    override fun new(): ModelState {
        return SettingsState()
    }

}

