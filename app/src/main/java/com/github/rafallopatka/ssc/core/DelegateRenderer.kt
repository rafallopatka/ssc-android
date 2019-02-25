package com.github.rafallopatka.ssc.core

import kotlin.reflect.KProperty

class DelegateRenderer<TModelState: ModelState>(val init: DelegateRenderer<TModelState>.() -> Unit): ModelStateRenderer<TModelState>(){
    private val renderActions: MutableMap<String, (state: TModelState) -> Unit>

    init {
        renderActions = mutableMapOf()
        init()
    }

    override fun renderProperty(property: String, modelState: TModelState) {
        renderActions.get(property)?.invoke(modelState)
    }

    infix fun KProperty<*>.renderWith(renderAction: (state: TModelState) -> Unit){
            renderActions[this.name] = renderAction
    }
}