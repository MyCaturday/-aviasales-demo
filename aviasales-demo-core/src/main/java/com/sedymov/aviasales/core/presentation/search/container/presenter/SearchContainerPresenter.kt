package com.sedymov.aviasales.core.presentation.search.container.presenter

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.search.container.view.SearchContainerView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter

class SearchContainerPresenter(
    loggingInteractor: LoggingInteractor,
    private val mSearchRouter: SearchRouter
) : BasePresenterWithLogging<SearchContainerView>(loggingInteractor) {

    override fun onCreate() {
        super.onCreate()

        mSearchRouter.moveToCitiesSelection()
    }
}