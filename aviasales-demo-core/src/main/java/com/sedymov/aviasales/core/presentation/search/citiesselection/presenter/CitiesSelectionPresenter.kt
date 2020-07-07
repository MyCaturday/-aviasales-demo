package com.sedymov.aviasales.core.presentation.search.citiesselection.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithTitle
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.CitiesSelectionView
import com.sedymov.aviasales.core.util.Empty
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables

class CitiesSelectionPresenter(
    loggingInteractor: LoggingInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers
) : BasePresenterWithTitle<CitiesSelectionView>(loggingInteractor) {

    override val mLoggingTag: String = "CitiesSelectionPresenter"

    fun onSearchCitiesButtonClicks(clicksListener: Observable<Any>) {

        clicksListener
            .subscribe(::onSearchButtonClicked, ::onSearchButtonClickFailure)
            .unsubscribeOnDestroy()
    }

    private fun onSearchButtonClicked(any: Any) {

        log.v("onSearchCitiesButtonClicked")
    }

    private fun onSearchButtonClickFailure(t: Throwable) {

        log.e(t)
        mMessagingInteractor.showErrorMessage(t.localizedMessage)
        mView.setSearchButtonEnabled(false)
    }

    fun onInputChanges(startCityListener: Observable<String>, destinationCityListener: Observable<String>) {

        Observables.combineLatest(
            startCityListener.startWith(String.Empty),
            destinationCityListener.startWith(String.Empty)
        ) { startCity, destinationCity ->
            isSearchButtonEnabled(startCity, destinationCity)
        }
            .subscribeOn(mRxSchedulers.ioScheduler)
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .subscribe(::setSearchButtonEnabled, ::onInputFieldsFailure )
            .unsubscribeOnDestroy()
    }

    private fun isSearchButtonEnabled(startCity: String, destinationCity: String): Boolean =
        startCity.isNotBlank() && destinationCity.isNotBlank()

    private fun setSearchButtonEnabled(isEnabled: Boolean) = mView.setSearchButtonEnabled(isEnabled)

    private fun onInputFieldsFailure(t: Throwable) {

        log.e(t)
        mMessagingInteractor.showErrorMessage(t.localizedMessage)
        mView.setSearchButtonEnabled(false)
    }
}