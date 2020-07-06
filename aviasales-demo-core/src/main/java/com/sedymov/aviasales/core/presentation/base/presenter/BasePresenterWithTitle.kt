package com.sedymov.aviasales.core.presentation.base.presenter

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.base.view.BaseViewWithTitle

abstract class BasePresenterWithTitle<V : BaseViewWithTitle>(
    loggingInteractor: LoggingInteractor
) : BasePresenterWithLogging<V>(loggingInteractor) {
}