package com.sedymov.aviasales.core.presentation.base.presenter

import com.sedymov.aviasales.core.presentation.base.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenterWithUnsubscribeOnDestroy<V : BaseView> : BasePresenter<V>() {

    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.unsubscribeOnDestroy(): Disposable {
        compositeDisposable.add(this)
        return this
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}