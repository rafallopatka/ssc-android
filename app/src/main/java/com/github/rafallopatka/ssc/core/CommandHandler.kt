package com.github.rafallopatka.ssc.core

import io.reactivex.Flowable

interface CommandHandler<TCommand : Command> {
    fun process(command: TCommand): Flowable<Unit>
}