package com.sedymov.aviasales.presentation.search.cityselection.startcityselection.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.presenter.StartCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.view.StartCitySelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.presentation.base.presenter.BaseMoxyPresenter
import com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view.StartCitySelectionMoxyView
import io.reactivex.Observable
import moxy.InjectViewState

@InjectViewState
class StartCitySelectionMoxyPresenter(
    private val loggingInteractor: LoggingInteractor,
    private val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers
): BaseMoxyPresenter<StartCitySelectionMoxyView>(), StartCitySelectionView {

    override fun providePresenter(): BasePresenter<BaseView> = StartCitySelectionPresenter(loggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mSearchRouter, mRxSchedulers) as BasePresenter<BaseView>

    private inline fun getPresenter(): StartCitySelectionPresenter =
        mPresenter as StartCitySelectionPresenter

    fun moveBack() = getPresenter().moveBack()

    fun onInputChanges(findViewListener: Observable<String>) {

        getPresenter().onInputChanges(findViewListener)
    }
}