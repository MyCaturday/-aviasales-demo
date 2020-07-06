package com.sedymov.aviasales.presentation.base.presenter

import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.presentation.base.view.BaseMoxyView
import moxy.MvpPresenter

abstract class BaseMoxyPresenter<V : BaseMoxyView> : MvpPresenter<V>(), BaseView {

    protected lateinit var mPresenter: BasePresenter<BaseView>

    abstract fun providePresenter() : BasePresenter<BaseView>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        mPresenter = providePresenter()
        mPresenter.setView(this)
        mPresenter.onCreate()
    }

    override fun destroyView(view: V) {
        super.destroyView(view)
        mPresenter.onDestroy()
    }
}