package com.github.rafallopatka.ssc

import com.github.rafallopatka.ssc.core.*
import com.github.rafallopatka.ssc.db.UnitOfWork
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


val appModule = Kodein.Module(name = "app") {
    bind<CommandBus>() with singleton { RxCommandBus() }
    bind<QueryBus>() with singleton { RxQueryBus() }
    bind() from provider { UnitOfWork() }
}


