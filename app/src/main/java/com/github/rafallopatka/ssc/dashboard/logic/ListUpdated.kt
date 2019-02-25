package com.github.rafallopatka.ssc.dashboard.logic

import com.github.rafallopatka.ssc.core.Action
import com.github.rafallopatka.ssc.core.Reaction

data class ListUpdated (override val action: Action, val list: Collection<Series>):
    Reaction