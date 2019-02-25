package com.github.rafallopatka.ssc.series.presentation

import com.github.rafallopatka.ssc.core.Pipeline
import com.github.rafallopatka.ssc.core.ReactiveModel
import com.github.rafallopatka.ssc.core.Reducer
import com.github.rafallopatka.ssc.series.logic.SeriesPipeline
import com.github.rafallopatka.ssc.series.logic.SeriesReducer
import com.github.rafallopatka.ssc.series.logic.SeriesState

class SeriesModel: ReactiveModel<SeriesState>(){
    override fun createReducer(): Reducer<SeriesState> {
        return SeriesReducer()
    }

    override fun createPipeline(): Pipeline<SeriesState> {
        return SeriesPipeline()
    }

    override fun createEmptyState(): SeriesState {
        return SeriesState()
    }
}