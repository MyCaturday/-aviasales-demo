package com.sedymov.aviasales.presentation.search.container.presenter

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.main.navigation.MainRouter
import com.sedymov.aviasales.core.presentation.main.presenter.MainPresenter
import com.sedymov.aviasales.core.presentation.main.view.MainView
import com.sedymov.aviasales.core.presentation.search.container.presenter.SearchContainerPresenter
import com.sedymov.aviasales.core.presentation.search.container.view.SearchContainerView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.presentation.base.presenter.BaseMoxyPresenter
import com.sedymov.aviasales.presentation.main.view.MainMoxyView
import com.sedymov.aviasales.presentation.search.container.view.SearchContainerMoxyView
import moxy.InjectViewState

@InjectViewState
class SearchContainerMoxyPresenter(
    private val mLoggingInteractor: LoggingInteractor,
    private val mRouter: SearchRouter
): BaseMoxyPresenter<SearchContainerMoxyView>(), SearchContainerView {

    override fun providePresenter() = SearchContainerPresenter(mLoggingInteractor, mRouter) as BasePresenter<BaseView>

    private inline fun getPresenter(): SearchContainerPresenter =
        mPresenter as SearchContainerPresenter
}