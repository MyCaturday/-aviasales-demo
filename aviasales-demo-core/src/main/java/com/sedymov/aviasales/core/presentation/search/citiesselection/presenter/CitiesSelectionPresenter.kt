package com.sedymov.aviasales.core.presentation.search.citiesselection.presenter

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenterWithTitle
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.CitiesSelectionView

class CitiesSelectionPresenter(
    loggingInteractor: LoggingInteractor,
    private val mSearchRouter: SearchRouter
) : BasePresenterWithTitle<CitiesSelectionView>(loggingInteractor) {

}