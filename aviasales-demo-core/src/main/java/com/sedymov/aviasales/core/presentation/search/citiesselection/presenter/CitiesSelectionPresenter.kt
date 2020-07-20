package com.sedymov.aviasales.core.presentation.search.citiesselection.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.CitiesSelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.repositories.search.citiesselection.CitiesSelectionResourcesRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import moxy.InjectViewState

@InjectViewState
class CitiesSelectionPresenter(
    loggingInteractor: LoggingInteractor,
    private val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mCitiesSelectionResourcesRepository: CitiesSelectionResourcesRepository,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers
) : BasePresenterWithLogging<CitiesSelectionView>(loggingInteractor) {

    override val mLoggingTag: String = "CitiesSelectionPresenter"

    private var mSelectedCities: Pair<City, City>? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        onCitiesSelection(
            mSearchCitiesInteractor.onStartCitySelected()
                .doOnNext { viewState.setStartCityName(it.city) },
            mSearchCitiesInteractor.onDestinationCitySelected()
                .doOnNext { viewState.setDestinationCityName(it.city) }
        )
    }

    fun moveBack() = mSearchRouter.moveBack()

    fun onStartCityButtonClicks(clicksListener: Observable<Any>) {

        clicksListener
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .subscribe(::onStartCityClicked, ::onStartCityClickFailure)
            .unsubscribeOnDestroy()
    }

    private fun onStartCityClicked(any: Any) {

        mSearchRouter.moveToStartCitySelectionScreen()
    }

    private fun onStartCityClickFailure(t: Throwable) = handleUnknownError(t)

    fun onDestinationCityButtonClicks(clicksListener: Observable<Any>) {

        clicksListener
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .subscribe(::onDestinationCityClicked, ::onDestinationCityClickFailure)
            .unsubscribeOnDestroy()
    }

    private fun onDestinationCityClicked(any: Any) {

        mSearchRouter.moveToDestinationCitySelectionScreen()
    }

    private fun onDestinationCityClickFailure(t: Throwable) = handleUnknownError(t)

    fun onSearchCitiesButtonClicks(clicksListener: Observable<Any>) {

        clicksListener
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .subscribe(::onSearchButtonClicked, ::onSearchButtonClickFailure)
            .unsubscribeOnDestroy()
    }

    private fun onSearchButtonClicked(any: Any) {

        mSelectedCities?.let { cities ->

            mSearchRouter.moveToSearchResult(cities)

        } ?: run {

            viewState.setSearchButtonEnabled(false)
        }
    }

    private fun onSearchButtonClickFailure(t: Throwable) = handleUnknownError(t)

    private fun onCitiesSelection(startCityListener: Observable<City>, destinationCityListener: Observable<City>) {

        Observables.combineLatest(
            startCityListener,
            destinationCityListener
        ) { startCity, destinationCity ->
            Pair(startCity, destinationCity)
        }
            .subscribeOn(mRxSchedulers.ioScheduler)
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .subscribe(::onCitiesSelected, ::onCitiesSelectionFailure )
            .unsubscribeOnDestroy()
    }

    private fun onCitiesSelected(cities: Pair<City, City>) {

        if (cities.first == cities.second) {

            viewState.showErrorMessage(mCitiesSelectionResourcesRepository.citiesAreIdenticalErrorText())
            viewState.setSearchButtonEnabled(false)

        } else {

            mSelectedCities = cities
            viewState.setSearchButtonEnabled(true)
        }
    }

    private fun onCitiesSelectionFailure(t: Throwable) = handleUnknownError(t)

    private fun handleUnknownError(t: Throwable) {

        log.e(t)
        viewState.showErrorMessage(t.localizedMessage)
        viewState.setSearchButtonEnabled(false)
    }
}