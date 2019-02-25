package com.github.rafallopatka.ssc.settings.actions

import com.github.rafallopatka.ssc.core.Action
import com.github.rafallopatka.ssc.core.Initialized
import com.github.rafallopatka.ssc.settings.logic.SettingsDto

class SettingsInitialized(action: Action, val settings: SettingsDto): Initialized(action)