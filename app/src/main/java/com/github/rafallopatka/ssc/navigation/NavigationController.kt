package com.github.rafallopatka.ssc.navigation

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Screen

object NavigationController {
    private var cicerone: Cicerone<Router>? = null

    init {
        initCicerone()
    }

    fun navigateTo(screen: Screen){
        this.router.navigateTo(screen)
    }

    fun replaceScreen(screen: Screen){
        this.router.replaceScreen(screen)
    }

    fun exit(){
        this.router.exit()
    }

    fun newRootScreen(screen: Screen){
        this.router.newRootScreen(screen)
    }

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    val navigatorHolder: NavigatorHolder
        get() {
            return cicerone!!.navigatorHolder
        }

    private val router: Router
        get() {
            return cicerone!!.getRouter()
        }
}

