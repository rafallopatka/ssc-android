package com.github.rafallopatka.ssc.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Screen

abstract class AndroidXAppScreen : Screen() {

    open fun getFragment(): Fragment? = null

    open fun getActivityIntent(context: Context): Intent? {
        return null
    }
}