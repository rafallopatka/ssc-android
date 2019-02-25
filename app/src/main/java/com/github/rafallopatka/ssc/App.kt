package com.github.rafallopatka.ssc

import android.app.Application
import com.github.rafallopatka.ssc.api.ApiDiscoveryObject
import com.github.rafallopatka.ssc.api.ApiFactory
import com.github.rafallopatka.ssc.core.QueryHandlerResolver
import com.github.rafallopatka.ssc.db.DatabaseInitializer
import com.facebook.stetho.Stetho
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule


class App : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(appModule)
        import(androidModule(this@App))
    }

    init {
        instance = this
    }

    override fun onCreate() {
        CommandBindings.init()
        QueryHandlerResolver.init(QueryBindings)
        DatabaseInitializer.run(this)
        ApiFactory.init()
        ApiDiscoveryObject.load()
        Stetho.initializeWithDefaults(this)
        super.onCreate()
    }

    companion object {
        lateinit var instance: App
    }
}
