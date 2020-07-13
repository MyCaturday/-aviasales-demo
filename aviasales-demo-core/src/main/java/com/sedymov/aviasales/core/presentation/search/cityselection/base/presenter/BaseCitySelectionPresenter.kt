package com.sedymov.aviasales.core.presentation.search.cityselection.base.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.search.cityselection.base.view.BaseCitySelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.util.unsubscribe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.Exceptions
import java.util.*
import java.util.concurrent.TimeUnit

abstract class BaseCitySelectionPresenter<V: BaseCitySelectionView>(
    loggingInteractor: LoggingInteractor,
    protected val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers
) : BasePresenterWithLogging<V>(loggingInteractor) {

    private var mLoadingDisposable: Disposable? = null

    fun moveBack() = mSearchRouter.moveBack()

    fun onInputChanges(findViewListener: Observable<String>) {

        findViewListener
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .doOnNext { showLoadingWithDelay() }
            .observeOn(mRxSchedulers.ioScheduler)
            .switchMapSingle { handleInterruptedException(it) }
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .doOnError { hideLoading() }
            .doAfterNext { hideLoading() }
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

    private fun showLoadingWithDelay() {

        unsubscribeLoading()

        mLoadingDisposable = Observable.timer(200L, TimeUnit.MILLISECONDS)
            .subscribeOn(mRxSchedulers.ioScheduler)
            .observeOn(mRxSchedulers.mainThreadScheduler)
            .subscribe(
                {
                    mView.showLoading(true)
                },
                {
                    log.e(it)
                }
            )
    }

    private fun hideLoading() {

        unsubscribeLoading()
        mView.showLoading(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribeLoading()
    }

    private fun unsubscribeLoading() {

        mLoadingDisposable.unsubscribe { log.e(it); Exceptions.throwIfFatal(it) }
        mLoadingDisposable = null
    }

    fun onCitySelected(city: City) {

        selectCity(city)
        mSearchRouter.moveBack()
    }

    protected abstract fun selectCity(city: City)
}