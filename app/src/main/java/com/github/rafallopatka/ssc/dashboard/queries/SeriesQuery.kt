package com.github.rafallopatka.ssc.dashboard.queries

import com.github.rafallopatka.ssc.core.Query
import com.github.rafallopatka.ssc.series.logic.SeriesDto

data class SeriesQuery(val skip: Int, val take: Int)
    : Query<Collection<SeriesDto>>()

