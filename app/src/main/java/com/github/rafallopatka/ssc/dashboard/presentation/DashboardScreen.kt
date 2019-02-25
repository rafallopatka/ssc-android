package com.github.rafallopatka.ssc.dashboard.presentation

import androidx.fragment.app.Fragment
import com.github.rafallopatka.ssc.navigation.AndroidXAppScreen

class DashboardScreen(): AndroidXAppScreen(){
    override fun getFragment(): Fragment? {
        val fragment = DashboardFragmentBuilder().build()
        return fragment
    }
}