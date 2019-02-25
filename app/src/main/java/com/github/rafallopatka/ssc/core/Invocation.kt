package com.github.rafallopatka.ssc.core

class Invocation<TValue> {
    var shouldBeHandled: Boolean = false
    var parameter: TValue? = null
}