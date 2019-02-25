package com.github.rafallopatka.ssc.settings.logic

import androidx.fragment.app.Fragment
import com.github.rafallopatka.ssc.navigation.AndroidXAppScreen
import com.github.rafallopatka.ssc.settings.presentation.SettingsFragmentBuilder

class SettingsScreen(): AndroidXAppScreen(){
    override fun getFragment(): Fragment? {
        val fragment = SettingsFragmentBuilder().build()
        return fragment
    }
}