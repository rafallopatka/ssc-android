package com.github.rafallopatka.ssc.series.presentation

import androidx.fragment.app.Fragment
import com.github.rafallopatka.ssc.navigation.AndroidXAppScreen
import com.github.rafallopatka.ssc.series.logic.SeriesInvokeArg

class SeriesScreen(private val arg: SeriesInvokeArg): AndroidXAppScreen(){
    override fun getFragment(): Fragment? {
        return SeriesFragmentBuilder(arg).build()
    }
}