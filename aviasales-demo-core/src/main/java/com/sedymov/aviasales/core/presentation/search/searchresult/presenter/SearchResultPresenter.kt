package com.sedymov.aviasales.core.presentation.search.searchresult.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.SphericalUtil
import com.sedymov.aviasales.core.presentation.base.TimeInterpolator
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.presentation.search.searchresult.view.SearchResultView
import com.sedymov.aviasales.core.util.unsubscribe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.Exceptions
import java.util.concurrent.TimeUnit

class SearchResultPresenter(
    loggingInteractor: LoggingInteractor,
    private val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers,
    private val mTimeInterpolator: TimeInterpolator,
    private val mSphericalUtil: SphericalUtil,
    private val mSelectedCities: Pair<City, City>
) : BasePresenterWithLogging<SearchResultView>(loggingInteractor){

    private class PlanePosition(val position: Pair<Double, Double>, val rotationAngle: Double)

    private lateinit var mStartCityLocation: Pair<Double, Double>
    private lateinit var mDestinationCityLocation: Pair<Double, Double>

    private val mTimerObservable = Observable.interval(ANIMATION_PERIOD, TimeUnit.MILLISECONDS)
        .map { Pair(it, System.currentTimeMillis()) }

    private var mTimerDisposable: Disposable? = null

    private var initialTimeValue = 0L

    fun moveBack() = mSearchRouter.moveBack()

    override fun onCreate() {
        super.onCreate()

        mStartCityLocation = Pair(mSelectedCities.first.location.lat, mSelectedCities.first.location.lon)
        mDestinationCityLocation = Pair(mSelectedCities.second.location.lat, mSelectedCities.second.location.lon)
    }

    private fun City.getVisibleName() =
        iata.getOrNull(0) ?: city

    fun onMapReady() {

        mView.setMarkerAtStartCity(mStartCityLocation, mSelectedCities.first.getVisibleName())
        mView.setMarkerAtDestinationCity(mDestinationCityLocation, mSelectedCities.second.getVisibleName())

        mView.drawLine(mStartCityLocation, mDestinationCityLocation)

        mView.setPlaneMarker(mStartCityLocation)

        mView.setCameraAt(mStartCityLocation, mDestinationCityLocation)

        if (mTimerDisposable == null) {

            mTimerDisposable = mTimerObservable
                .subscribeOn(mRxSchedulers.ioScheduler)
                .doOnNext { pair -> if (pair.first == 0L) { initialTimeValue = pair.second } }
                .map { pair -> pair.second }
                .map { time -> onTimer(time) }
                .observeOn(mRxSchedulers.mainThreadScheduler)
                .subscribe(::onPlanePosition, ::onTimerError)
        }
    }

    private fun onTimer(timeMS: Long): PlanePosition {

        val elapsedTime = timeMS - initialTimeValue
        val animationPercent = mTimeInterpolator.getInterpolation(elapsedTime.toFloat() / ANIMATION_LENGTH)

        val currentLatLng = mSphericalUtil.interpolate(mStartCityLocation, mDestinationCityLocation, animationPercent.toDouble())

        val nextAnimationPercent = mTimeInterpolator.getInterpolation((elapsedTime.toFloat() + ANIMATION_PERIOD) / ANIMATION_LENGTH)
        val nextLatLng = mSphericalUtil.interpolate(mStartCityLocation, mDestinationCityLocation, nextAnimationPercent.toDouble())

        val rotationAngle = mSphericalUtil.computeHeading(currentLatLng, nextLatLng) + PLANE_SPRITE_ANGLE_TO_NORTH

        if (elapsedTime > ANIMATION_LENGTH) {
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

    private companion object {

        private const val PLANE_SPRITE_ANGLE_TO_NORTH = -90

        private const val ANIMATION_LENGTH = 6000L
        private const val ANIMATION_PERIOD = 1000 / 60L
    }
}