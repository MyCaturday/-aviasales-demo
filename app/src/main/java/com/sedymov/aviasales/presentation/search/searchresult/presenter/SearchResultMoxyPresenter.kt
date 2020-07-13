package com.sedymov.aviasales.presentation.search.searchresult.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.SphericalUtil
import com.sedymov.aviasales.core.presentation.base.TimeInterpolator
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
    private val mRxSchedulers: RxSchedulers,
    private val mTimeInterpolator: TimeInterpolator,
    private val mSphericalUtil: SphericalUtil,
    private val mSelectedCities: Pair<City, City>
): BaseMoxyPresenter<SearchResultMoxyView>(), SearchResultView {

    override fun providePresenter(): BasePresenter<BaseView> = SearchResultPresenter(mLoggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mRouter, mRxSchedulers, mTimeInterpolator, mSphericalUtil, mSelectedCities) as BasePresenter<BaseView>

    private inline fun getPresenter() = mPresenter as SearchResultPresenter

    override fun setMarkerAtStartCity(point: Pair<Double, Double>, name: String) = viewState.setMarkerAtStartCity(point, name)

    override fun setMarkerAtDestinationCity(point: Pair<Double, Double>, name: String) = viewState.setMarkerAtDestinationCity(point, name)

    override fun drawLine(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>) = viewState.drawLine(firstPoint, secondPoint)

    override fun setPlaneMarker(point: Pair<Double, Double>) = viewState.setPlaneMarker(point)

    override fun setPlaneMarkerPosition(point: Pair<Double, Double>) = viewState.setPlaneMarkerPosition(point)

    override fun setPlaneMarkerRotation(rotation: Float) = viewState.setPlaneMarkerRotation(rotation)

    override fun setCameraAt(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>) = viewState.setCameraAt(firstPoint, secondPoint)

    fun moveBack() = getPresenter().moveBack()

    fun onMapReady() {

        getPresenter().onMapReady()
    }
}