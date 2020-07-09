package com.sedymov.aviasales.presentation.search.cityselection.destinationcityselection.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.search.cityselection.destinationcityselection.presenter.DestinationCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.destinationcityselection.view.DestinationCitySelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.presentation.search.cityselection.base.presenter.BaseCitySelectionMoxyPresenter
import com.sedymov.aviasales.presentation.search.cityselection.destinationcityselection.view.DestinationCitySelectionMoxyView
import moxy.InjectViewState


@InjectViewState
class DestinationCitySelectionMoxyPresenter(
    loggingInteractor: LoggingInteractor,
    searchCitiesInteractor: SearchCitiesInteractor,
    messagingInteractor: MessagingInteractor,
    searchRouter: SearchRouter,
    rxSchedulers: RxSchedulers
): BaseCitySelectionMoxyPresenter<DestinationCitySelectionMoxyView>(loggingInteractor, searchCitiesInteractor, messagingInteractor, searchRouter, rxSchedulers),
    DestinationCitySelectionView {

    override fun providePresenter(
        loggingInteractor: LoggingInteractor,
        searchCitiesInteractor: SearchCitiesInteractor,
        messagingInteractor: MessagingInteractor,
        searchRouter: SearchRouter,
        rxSchedulers: RxSchedulers
    ): BasePresenter<BaseView> =
        DestinationCitySelectionPresenter(loggingInteractor, searchCitiesInteractor, messagingInteractor, searchRouter, rxSchedulers) as BasePresenter<BaseView>
}