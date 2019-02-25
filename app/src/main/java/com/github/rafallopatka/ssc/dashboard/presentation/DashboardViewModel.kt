package com.github.rafallopatka.ssc.dashboard.presentation

import com.github.rafallopatka.ssc.core.Pipeline
import com.github.rafallopatka.ssc.core.ReactiveModel
import com.github.rafallopatka.ssc.core.Reducer
import com.github.rafallopatka.ssc.core.ServiceResolver
import com.github.rafallopatka.ssc.dashboard.logic.DashboardPipeline
import com.github.rafallopatka.ssc.dashboard.logic.DashboardReducer
import com.github.rafallopatka.ssc.dashboard.logic.DashboardState

class DashboardViewModel : ReactiveModel<DashboardState>() {
    override fun createReducer(): Reducer<DashboardState> {
        return DashboardReducer()
    }

    override fun createPipeline(): Pipeline<DashboardState> {
        return DashboardPipeline(ServiceResolver.get())
    }

    override fun createEmptyState(): DashboardState {
        return DashboardState()
    }

}


