package com.github.rafallopatka.ssc.series.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.rafallopatka.ssc.R
import com.github.rafallopatka.ssc.core.*
import com.github.rafallopatka.ssc.dashboard.presentation.DashboardScreen
import com.github.rafallopatka.ssc.navigation.NavigationController
import com.github.rafallopatka.ssc.series.logic.SeriesInvokeArg
import com.github.rafallopatka.ssc.series.logic.SeriesState
import com.github.rafallopatka.ssc.series.actions.ChangeName
import com.github.rafallopatka.ssc.series.actions.ChangeSequence
import com.github.rafallopatka.ssc.series.actions.Confirm
import com.github.rafallopatka.ssc.series.actions.SeriesInitialize
import com.github.rafallopatka.ssc.title
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.fragment_series.*
import java.util.concurrent.TimeUnit

@FragmentWithArgs
class SeriesFragment : ReactiveFragment<SeriesState>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_series, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textSequence.filters = arrayOf(SpecialCharacterInputFilter())

        textSequence
            .textChanges()
            .debounce(200, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .distinctUntilChanged()
            .invokeAction { ChangeSequence(it) }

        textName
            .textChanges()
            .debounce(200, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .distinctUntilChanged()
            .invokeAction { ChangeName(it) }

        confirmButton.clicks()
            .invokeAction { Confirm(arg) }
    }

    @Arg
    lateinit var arg: SeriesInvokeArg

    override fun initializeAction(): Initialize {
        return SeriesInitialize(arg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
    }

    override fun getViewModelClass(): Class<*> {
        return SeriesModel::class.java
    }

    override fun createRenderer(): ModelStateRenderer<SeriesState> {
        return DelegateRenderer {
            SeriesState::name renderWith {
                updateNameField(it)
            }
            SeriesState::sequence renderWith {
                updateSequenceField(it)
            }
            SeriesState::leave renderWith {
                leaveView(it)
            }
            SeriesState::lockInputs renderWith {
                lockInputs(it.lockInputs.get())
            }
            SeriesState::mode renderWith {
                updateViewMode(it)
            }
        }
    }

    private fun updateViewMode(state: SeriesState) {
        val entityName = state.entity.get().name

        when (state.mode.get()) {
            ViewMode.Edit -> {
                title = "Edit - $entityName"
                confirmButton.setImageResource(R.drawable.ic_save)
            }
            ViewMode.View -> {
                title = "Run - $entityName"
                confirmButton.setImageResource(R.drawable.ic_play)
            }
            ViewMode.Delete -> {
                title = "Delete - $entityName"

                confirmButton.setImageResource(R.drawable.ic_remove)
            }
            ViewMode.Create -> {
                title = "Create new sequence"

                confirmButton.setImageResource(R.drawable.ic_save)
            }
        }
    }

    private fun lockInputs(lock: Boolean) {
        textSequence.isEnabled = lock == false
        textName.isEnabled = lock == false
    }

    private fun leaveView(state: SeriesState) {
        if (state.leave.shouldBeHandled() == false) {
            return
        }

        state.leave.handle()
        NavigationController.replaceScreen(DashboardScreen())
    }

    private fun updateNameField(state: SeriesState) {
        val name = state.name.get()
        this.textName.setText(name)
    }

    private fun updateSequenceField(state: SeriesState) {
        val sequence = state.sequence.get()
        this.textSequence.setText(sequence)
    }
}

