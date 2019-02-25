package com.github.rafallopatka.ssc.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.toLiveData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

abstract class ReactiveFragment<TModelState: ModelState> : Fragment() {
    private lateinit var model: ReactiveModel<TModelState>
    private lateinit var renderer: ModelStateRenderer<TModelState>

    protected abstract fun getViewModelClass(): Class<*>
    protected abstract fun createRenderer(): ModelStateRenderer<TModelState>

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.renderer = createRenderer()
        this.model = ViewModelProviders.of(this)
            .get(getViewModelClass() as Class<ReactiveModel<TModelState>>)

        model.react()
            .toLiveData().observe(this, Observer<TModelState> { state ->
            if (this.isResumed) {
                renderer.render(state)
            }
        })
    }

    protected open fun initializeAction(): Initialize {
        return Initialize()
    }

    override fun onResume() {
        super.onResume()
        if (model.isNotAttached()) {
            model.attach()
            model.invoke(initializeAction())
        } else {
            model.invoke(Restore())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }


    protected fun invokeAction(action: Action){
        model.invoke(action)
    }

    protected fun <TAny> Observable<TAny>.invokeAction(actionTransform: ((TAny) -> Action)){
        this.subscribe {
            invokeAction(actionTransform(it))
        }.addTo(disposables)

    }
    private val disposables = CompositeDisposable()
}