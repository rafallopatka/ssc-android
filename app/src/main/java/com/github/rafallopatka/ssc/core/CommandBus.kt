package com.github.rafallopatka.ssc.core

import kotlin.reflect.KClass

interface CommandBus {
    fun dispatch(command: Command)
    fun <TCommand: Command, TCommandHandler: CommandHandler<TCommand>> bind(clazz: KClass<TCommand>, handler: TCommandHandler)
}

