package com.sedymov.aviasales.core.presentation.search.cityselection.base.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.search.cityselection.base.view.BaseCitySelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

abstract class BaseCitySelectionPresenter<V: BaseCitySelectionView>(
    loggingInteractor: LoggingInteractor,
    protected val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers
) : BasePresenterWithLogging<V>(loggingInteractor) {

    fun moveBack() = mSearchRouter.moveBack()

    fun onInputChanges(findViewListener: Observable<String>) {

        findViewListener
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .doOnNext { mView.showLoading(true) }
            .observeOn(mRxSchedulers.ioScheduler)
            .switchMapSingle { handleInterruptedException(it) }
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .doOnError { mView.showLoading(false) }
            .doAfterNext { mView.showLoading(false) }
            .subscribe(::onSearchInput, ::onSearchInputFailure )
            .unsubscribeOnDestroy()
    }

    private fun handleInterruptedException(searchString: String): Single<List<City>> =
        mSearchCitiesInteractor
            .getCities(searchString)
            .onErrorResumeNext { t : Throwable ->

                if (t is java.io.InterruptedIOException) {

                    Single.just(ArrayList<City>())
                }
                else {

                    Single.error(t)
                }
            }

    private fun onSearchInput(cities: List<City>) {

        showCities(cities)
    }

    private fun onSearchInputFailure(t: Throwable) {

        log.e(t)
        mMessagingInteractor.showErrorMessage(t.localizedMessage)
    }

    private fun showCities(cities: List<City>) {

        mView.showCities(cities)
    }

    fun onCitySelected(city: City) {

        selectCity(city)
        mSearchRouter.moveBack()
    }

    protected abstract fun selectCity(city: City)
}