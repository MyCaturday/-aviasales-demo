package com.sedymov.aviasales.core.presentation.main.presenter

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.main.navigation.MainRouter
import com.sedymov.aviasales.core.presentation.main.view.MainView
import moxy.InjectViewState

@InjectViewState
class MainPresenter(
    loggingInteractor: LoggingInteractor,
    private val mRouter: MainRouter
) : BasePresenterWithLogging<MainView>(loggingInteractor) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        mRouter.moveToSearchScreen()
    }
}