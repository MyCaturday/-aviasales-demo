package com.sedymov.aviasales.core.presentation.base.presenter

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.util.LoggingHelper
import com.sedymov.aviasales.core.presentation.base.view.BaseView

abstract class BasePresenterWithLogging<V : BaseView> (
    private val mLoggingInteractor: LoggingInteractor
) : BasePresenterWithUnsubscribeOnDestroy<V>() {

    protected open val mLoggingTag: String by lazy { javaClass.simpleName }

    protected val log: LoggingHelper by lazy { LoggingHelper(mLoggingTag, mLoggingInteractor) }
}