package com.github.rafallopatka.ssc.series.queries

import com.github.rafallopatka.ssc.core.Query
import com.github.rafallopatka.ssc.series.logic.SeriesDto
import java.util.*

data class GetSeriesByIdQuery(val id: UUID): Query<SeriesDto>()

