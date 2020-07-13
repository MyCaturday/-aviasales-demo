package com.sedymov.aviasales.presentation.search.cityselection.startcityselection.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.presenter.StartCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.view.StartCitySelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.presentation.search.cityselection.base.presenter.BaseCitySelectionMoxyPresenter
import com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view.StartCitySelectionMoxyView
import moxy.InjectViewState

@InjectViewState
class StartCitySelectionMoxyPresenter(
    loggingInteractor: LoggingInteractor,
    searchCitiesInteractor: SearchCitiesInteractor,
    messagingInteractor: MessagingInteractor,
    searchRouter: SearchRouter,
    rxSchedulers: RxSchedulers
): BaseCitySelectionMoxyPresenter<StartCitySelectionMoxyView>(loggingInteractor, searchCitiesInteractor, messagingInteractor, searchRouter, rxSchedulers), StartCitySelectionView {

    override fun providePresenter(
        loggingInteractor: LoggingInteractor,
        searchCitiesInteractor: SearchCitiesInteractor,
        messagingInteractor: MessagingInteractor,
        searchRouter: SearchRouter,
        rxSchedulers: RxSchedulers
    ): BasePresenter<BaseView> =
        StartCitySelectionPresenter(loggingInteractor, searchCitiesInteractor, messagingInteractor, searchRouter, rxSchedulers) as BasePresenter<BaseView>
}