package com.github.rafallopatka.ssc.core

import com.github.rafallopatka.ssc.App
import org.kodein.di.direct
import org.kodein.di.generic.instance


object ServiceResolver{
    inline fun <reified TType : Any> get(): TType {
        return App.instance.kodein.direct.instance()
    }
}