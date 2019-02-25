package com.github.rafallopatka.ssc.dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rafallopatka.ssc.R
import com.github.rafallopatka.ssc.core.DelegateRenderer
import com.github.rafallopatka.ssc.core.ModelStateRenderer
import com.github.rafallopatka.ssc.core.ReactiveFragment
import com.github.rafallopatka.ssc.dashboard.logic.DashboardState
import com.github.rafallopatka.ssc.series.logic.SeriesInvokeArg
import com.github.rafallopatka.ssc.series.presentation.SeriesScreen
import com.github.rafallopatka.ssc.navigation.NavigationController
import com.github.rafallopatka.ssc.title
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import kotlinx.android.synthetic.main.fragment_dashboard.*

@FragmentWithArgs
class DashboardFragment : ReactiveFragment<DashboardState>() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: SeriesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(this.context)
        viewAdapter = SeriesAdapter(this.context!!) {
            when (it) {
                is SeriesAdapter.Action.View -> NavigationController.navigateTo(
                    SeriesScreen(
                        SeriesInvokeArg.Run(it.series.id)
                    )
                )
                is SeriesAdapter.Action.Edit -> NavigationController.navigateTo(
                    SeriesScreen(
                        SeriesInvokeArg.Edit(it.series.id)
                    )
                )
                is SeriesAdapter.Action.Delete -> NavigationController.navigateTo(
                    SeriesScreen(
                        SeriesInvokeArg.Delete(it.series.id)
                    )
                )
            }
        }

        recyclerView = seriesListView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        addNewSeries.setOnClickListener {
            NavigationController.navigateTo(SeriesScreen(SeriesInvokeArg.Create()))
        }

        title = "Dashboard"
    }

    override fun getViewModelClass(): Class<*> {
        return DashboardViewModel::class.java
    }

    override fun createRenderer(): ModelStateRenderer<DashboardState> {
        return DelegateRenderer {
            DashboardState::initialized renderWith {

            }
            DashboardState::data renderWith {
                viewAdapter.setData(it.data.get())
            }
        }
    }
}

