package com.github.rafallopatka.ssc.core

import io.reactivex.BackpressureStrategy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlin.reflect.KClass

class RxCommandBus : CommandBus {
    private val handlersMap = mutableMapOf<String, CommandHandler<Command>>()
    private val stream = PublishSubject.create<Command>()

    override fun dispatch(command: Command) {
        stream.onNext(command)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <TCommand : Command, TCommandHandler : CommandHandler<TCommand>> bind(
        clazz: KClass<TCommand>,
        handler: TCommandHandler
    ) {
        handlersMap[clazz.java.name] = handler as CommandHandler<Command>
    }

    init {
        stream
            .toFlowable(BackpressureStrategy.BUFFER)
            .observeOn(Schedulers.io())
            .parallel()
            .flatMap { command ->
                handlersMap[command.javaClass.name]?.process(command)?.onErrorReturn { Unit }
            }
            .sequentialDelayError()
            .onErrorReturn { Unit }
            .subscribe()
    }
}