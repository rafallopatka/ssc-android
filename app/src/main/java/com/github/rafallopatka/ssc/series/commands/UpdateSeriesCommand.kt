package com.github.rafallopatka.ssc.series.commands

import com.github.rafallopatka.ssc.core.Command
import com.github.rafallopatka.ssc.series.logic.SeriesDto

data class UpdateSeriesCommand(val entity: SeriesDto) : Command