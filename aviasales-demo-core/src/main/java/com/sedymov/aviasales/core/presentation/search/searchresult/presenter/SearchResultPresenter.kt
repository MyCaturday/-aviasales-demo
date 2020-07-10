package com.sedymov.aviasales.core.presentation.search.searchresult.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithLogging
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.CitiesSelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.presentation.search.searchresult.view.SearchResultView

class SearchResultPresenter(
    loggingInteractor: LoggingInteractor,
    private val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers,
    private val mSelectedCities: Pair<City, City>
) : BasePresenterWithLogging<SearchResultView>(loggingInteractor){

    fun moveBack() = mSearchRouter.moveBack()

    fun onMapReady() {

        with (mSelectedCities.first) {

            mView.setMarkerAtStartCity(location.lat, location.lon, city)
        }

        with (mSelectedCities.second) {

            mView.setMarkerAtDestinationCity(location.lat, location.lon, city)
        }

        val startCityLocation = Pair(mSelectedCities.first.location.lat, mSelectedCities.first.location.lon)
        val destinationCityLocation = Pair(mSelectedCities.second.location.lat, mSelectedCities.second.location.lon)
        mView.drawLine(startCityLocation, destinationCityLocation)
    }
}