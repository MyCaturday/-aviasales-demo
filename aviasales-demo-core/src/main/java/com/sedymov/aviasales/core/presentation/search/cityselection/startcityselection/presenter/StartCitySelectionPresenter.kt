package com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.view.StartCitySelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import io.reactivex.Observable

class StartCitySelectionPresenter(
    loggingInteractor: LoggingInteractor,
    private val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers
) : BasePresenterWithLogging<StartCitySelectionView>(loggingInteractor) {

    fun moveBack() = mSearchRouter.moveBack()

    fun onInputChanges(findViewListener: Observable<String>) {

        findViewListener
            .switchMapSingle { mSearchCitiesInteractor.getCities(it) }
            .subscribeOn(mRxSchedulers.ioScheduler)
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .subscribe(::onSearchInput, ::onSearchInputFailure )
            .unsubscribeOnDestroy()
    }

    private fun onSearchInput(cities: List<City>) {

        log.v("onSearchInput")
    }

    private fun onSearchInputFailure(t: Throwable) {

        log.e(t)
        mMessagingInteractor.showErrorMessage(t.localizedMessage)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}