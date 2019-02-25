package com.github.rafallopatka.ssc

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.github.rafallopatka.ssc.dashboard.presentation.DashboardScreen
import com.github.rafallopatka.ssc.navigation.AppScreenNavigator
import com.github.rafallopatka.ssc.navigation.NavigationController
import com.github.rafallopatka.ssc.settings.logic.SettingsScreen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            NavigationController.replaceScreen(DashboardScreen())
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_dashboard -> {
                NavigationController.replaceScreen(DashboardScreen())
            }
            R.id.nav_settings -> {
                NavigationController.replaceScreen(SettingsScreen())
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    val navigator by lazy {
        AppScreenNavigator(this, R.id.nav_host_fragment)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        NavigationController.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        NavigationController.navigatorHolder.removeNavigator()
        super.onPause()
    }
}

var Fragment.title: String
    get() {
        val toolbar = (activity as MainActivity).supportActionBar!!

        return toolbar.title.toString()
    }
    set(value) {
        val toolbar = (activity as MainActivity).supportActionBar!!
        toolbar.title = value
    }

