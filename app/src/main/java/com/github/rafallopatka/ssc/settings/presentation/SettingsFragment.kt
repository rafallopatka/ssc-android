package com.github.rafallopatka.ssc.settings.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.rafallopatka.ssc.R
import com.github.rafallopatka.ssc.core.DelegateRenderer
import com.github.rafallopatka.ssc.core.ModelStateRenderer
import com.github.rafallopatka.ssc.core.ReactiveFragment
import com.github.rafallopatka.ssc.settings.actions.ChangeApiAddress
import com.github.rafallopatka.ssc.settings.logic.SettingsState
import com.github.rafallopatka.ssc.title
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.fragment_settings.*

@FragmentWithArgs
class SettingsFragment : ReactiveFragment<SettingsState>() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.settings)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confirmButton.clicks()
            .map { textAddress.text.toString() }
            .invokeAction { ChangeApiAddress(it) }
    }

    override fun getViewModelClass(): Class<*> {
        return SettingsModel::class.java
    }

    override fun createRenderer(): ModelStateRenderer<SettingsState> {
        return DelegateRenderer {
            SettingsState::address renderWith { textAddress.setText(it.address.get()) }
        }
    }
}
