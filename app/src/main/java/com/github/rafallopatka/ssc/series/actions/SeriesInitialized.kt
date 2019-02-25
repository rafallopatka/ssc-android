package com.github.rafallopatka.ssc.series.actions

import com.github.rafallopatka.ssc.core.Action
import com.github.rafallopatka.ssc.core.Initialized
import com.github.rafallopatka.ssc.series.logic.SeriesDto
import com.github.rafallopatka.ssc.series.logic.SeriesInvokeArg

class SeriesInitialized(
    action: Action,
    val entity: SeriesDto,
    val invokeArg: SeriesInvokeArg
): Initialized(action)