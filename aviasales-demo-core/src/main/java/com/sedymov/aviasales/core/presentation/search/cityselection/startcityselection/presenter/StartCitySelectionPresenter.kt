package com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.search.cityselection.base.presenter.BaseCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.view.StartCitySelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter

class StartCitySelectionPresenter(
    loggingInteractor: LoggingInteractor,
    searchCitiesInteractor: SearchCitiesInteractor,
    messagingInteractor: MessagingInteractor,
    searchRouter: SearchRouter,
    rxSchedulers: RxSchedulers
) : BaseCitySelectionPresenter<StartCitySelectionView>(loggingInteractor, searchCitiesInteractor, messagingInteractor, searchRouter, rxSchedulers) {

    override fun selectCity(city: City) = mSearchCitiesInteractor.selectStartCity(city)
}