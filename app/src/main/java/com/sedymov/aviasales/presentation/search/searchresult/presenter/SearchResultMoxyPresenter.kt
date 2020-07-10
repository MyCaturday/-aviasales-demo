package com.sedymov.aviasales.presentation.search.searchresult.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.presentation.search.searchresult.presenter.SearchResultPresenter
import com.sedymov.aviasales.core.presentation.search.searchresult.view.SearchResultView
import com.sedymov.aviasales.presentation.base.presenter.BaseMoxyPresenter
import com.sedymov.aviasales.presentation.search.searchresult.view.SearchResultMoxyView
import moxy.InjectViewState

@InjectViewState
class SearchResultMoxyPresenter(
    private val mLoggingInteractor: LoggingInteractor,
    private val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers
): BaseMoxyPresenter<SearchResultMoxyView>(), SearchResultView {

    override fun providePresenter(): BasePresenter<BaseView> = SearchResultPresenter(mLoggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mRouter, mRxSchedulers) as BasePresenter<BaseView>
}