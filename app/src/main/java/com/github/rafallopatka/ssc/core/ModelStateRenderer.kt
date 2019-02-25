package com.github.rafallopatka.ssc.core

abstract class ModelStateRenderer<TModelState: ModelState> {
    private var prevModelState: TModelState? = null

    fun render(modelState: TModelState) {
        val propertiesToRender = getPropertiesToRender(modelState)

        for (property in propertiesToRender){
            renderProperty(property, modelState)
        }

        prevModelState = modelState
    }

    protected abstract fun renderProperty(property: String, modelState: TModelState)

    private fun getPropertiesToRender(modelState: ModelState): Collection<String> {
        val propertiesToRender = arrayListOf<String>()
        for (newPropState in modelState.state) {
            val oldPropState = prevModelState?.state?.get(newPropState.key)

            if (oldPropState == null) {
                propertiesToRender.add(newPropState.key)
                continue
            }

            if (oldPropState.lastModification < newPropState.value.lastModification){
                propertiesToRender.add(newPropState.key)
            }
        }

        return propertiesToRender
    }
}