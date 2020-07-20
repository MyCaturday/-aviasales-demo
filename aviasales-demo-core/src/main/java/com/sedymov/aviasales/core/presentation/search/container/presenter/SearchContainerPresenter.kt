package com.sedymov.aviasales.core.presentation.search.container.presenter

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.search.container.view.SearchContainerView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import moxy.InjectViewState

@InjectViewState
class SearchContainerPresenter(
    loggingInteractor: LoggingInteractor,
    private val mSearchRouter: SearchRouter
) : BasePresenterWithLogging<SearchContainerView>(loggingInteractor) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        mSearchRouter.moveToCitiesSelection()
    }
}