package com.github.rafallopatka.ssc.core

object QueryHandlerResolver{
    fun init(registry: QueryHandlerRegistry){
        this.registry = registry
    }

    lateinit var registry: QueryHandlerRegistry
}