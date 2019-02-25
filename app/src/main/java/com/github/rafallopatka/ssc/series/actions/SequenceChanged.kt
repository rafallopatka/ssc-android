package com.github.rafallopatka.ssc.series.actions

import com.github.rafallopatka.ssc.core.Action
import com.github.rafallopatka.ssc.core.Reaction

class SequenceChanged(override val action: Action, val sequence: String):
    Reaction