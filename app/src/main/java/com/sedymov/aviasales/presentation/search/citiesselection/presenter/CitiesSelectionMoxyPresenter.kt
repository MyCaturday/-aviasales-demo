package com.sedymov.aviasales.presentation.search.citiesselection.presenter

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.search.citiesselection.presenter.CitiesSelectionPresenter
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.CitiesSelectionView
import com.sedymov.aviasales.core.presentation.search.container.presenter.SearchContainerPresenter
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.presentation.base.presenter.BaseMoxyPresenter
import com.sedymov.aviasales.presentation.search.citiesselection.view.CitiesSelectionMoxyView
import moxy.InjectViewState

@InjectViewState
class CitiesSelectionMoxyPresenter(
    private val mLoggingInteractor: LoggingInteractor,
    private val mRouter: SearchRouter
): BaseMoxyPresenter<CitiesSelectionMoxyView>(), CitiesSelectionView {

    override fun providePresenter() = CitiesSelectionPresenter(mLoggingInteractor, mRouter) as BasePresenter<BaseView>

    override fun setTitle(title: String) = viewState.setTitle(title)
}