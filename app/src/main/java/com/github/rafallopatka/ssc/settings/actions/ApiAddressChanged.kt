package com.github.rafallopatka.ssc.settings.actions

import com.github.rafallopatka.ssc.core.Action
import com.github.rafallopatka.ssc.core.Reaction

data class ApiAddressChanged(override val action: Action, val address: String) : Reaction