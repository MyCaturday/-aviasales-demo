package com.sedymov.aviasales.core.presentation.base.presenter

import com.sedymov.aviasales.core.presentation.base.view.BaseView

abstract class BasePresenter <V : BaseView> {

    protected lateinit var mView: V

    open fun setView(view: V) {
        mView = view
    }

    open fun onCreate() {
    }

    open fun onDestroy() {
    }
}