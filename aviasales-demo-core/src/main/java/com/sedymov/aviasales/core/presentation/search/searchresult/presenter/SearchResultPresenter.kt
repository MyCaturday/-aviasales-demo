package com.sedymov.aviasales.core.presentation.search.searchresult.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.SphericalUtil
import com.sedymov.aviasales.core.presentation.base.TimeInterpolator
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.CitiesSelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.presentation.search.searchresult.view.SearchResultView
import com.sedymov.aviasales.core.util.currentThreadName
import com.sedymov.aviasales.core.util.unsubscribe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.Exceptions
import java.util.concurrent.TimeUnit

class SearchResultPresenter(
    loggingInteractor: LoggingInteractor,
    private val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers,
    private val mTimeInterpolator: TimeInterpolator,
    private val mSphericalUtil: SphericalUtil,
    private val mSelectedCities: Pair<City, City>
) : BasePresenterWithLogging<SearchResultView>(loggingInteractor){

    private class PlanePosition(val position: Pair<Double, Double>, val rotationAngle: Double)

    private lateinit var startCityLocation: Pair<Double, Double>
    private lateinit var destinationCityLocation: Pair<Double, Double>

    private val mDuration = 6000L
    private val mPeriod = 1000 / 60L
    private val timerObservable = Observable.interval(mPeriod, TimeUnit.MILLISECONDS)
        .map { Pair(it, System.currentTimeMillis()) }

    private var mTimerDisposable: Disposable? = null

    private var initialTimeValue = 0L

    fun moveBack() = mSearchRouter.moveBack()

    override fun onCreate() {
        super.onCreate()

        startCityLocation = Pair(mSelectedCities.first.location.lat, mSelectedCities.first.location.lon)
        destinationCityLocation = Pair(mSelectedCities.second.location.lat, mSelectedCities.second.location.lon)
    }

    private fun City.getVisibleName() =
        iata.getOrNull(0) ?: city

    fun onMapReady() {

        mView.setMarkerAtStartCity(startCityLocation, mSelectedCities.first.getVisibleName())
        mView.setMarkerAtDestinationCity(destinationCityLocation, mSelectedCities.second.getVisibleName())

        mView.drawLine(startCityLocation, destinationCityLocation)

        mView.setPlaneMarker(startCityLocation)

        mView.setCameraAt(startCityLocation, destinationCityLocation)

        if (mTimerDisposable == null) {

            mTimerDisposable = timerObservable
                .subscribeOn(mRxSchedulers.ioScheduler)
                .doOnNext { pair -> if (pair.first == 0L) { initialTimeValue = pair.second } }
                .map { pair -> pair.second }
                .map { time -> onTimer(time) }
                .observeOn(mRxSchedulers.mainThreadScheduler)
                .subscribe(::onPlanePosition, ::onTimerError)
        }
    }

    private fun onTimer(timeMS: Long): PlanePosition {

        val elapsed = timeMS - initialTimeValue
        val animationPercent = mTimeInterpolator.getInterpolation(elapsed.toFloat() / mDuration)

        val currentLatLng = mSphericalUtil.interpolate(startCityLocation, destinationCityLocation, animationPercent.toDouble())

        val nextAnimationPercent = mTimeInterpolator.getInterpolation((elapsed.toFloat() + mPeriod) / mDuration)
        val nextLatLng = mSphericalUtil.interpolate(startCityLocation, destinationCityLocation, nextAnimationPercent.toDouble())

        val rotationAngle = mSphericalUtil.computeHeading(currentLatLng, nextLatLng) - 90

        if (elapsed > mDuration) {
            unsubscribeTimer()
        }

        return PlanePosition(currentLatLng, rotationAngle)
    }

    private fun onPlanePosition(planePosition: PlanePosition) {

        mView.setPlaneMarkerPosition(Pair(planePosition.position.first, planePosition.position.second))
        mView.setPlaneMarkerRotation(planePosition.rotationAngle.toFloat())
    }

    private fun onTimerError(t: Throwable) {

        log.e(t)
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribeTimer()
    }

    private fun unsubscribeTimer() {

        mTimerDisposable.unsubscribe { log.e(it); Exceptions.throwIfFatal(it) }
        mTimerDisposable = null
    }
}