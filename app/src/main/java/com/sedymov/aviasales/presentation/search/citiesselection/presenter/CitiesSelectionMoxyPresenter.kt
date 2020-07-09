package com.sedymov.aviasales.presentation.search.citiesselection.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.search.citiesselection.presenter.CitiesSelectionPresenter
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.CitiesSelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.presentation.base.presenter.BaseMoxyPresenter
import com.sedymov.aviasales.presentation.search.citiesselection.view.CitiesSelectionMoxyView
import io.reactivex.Observable
import moxy.InjectViewState

@InjectViewState
class CitiesSelectionMoxyPresenter(
    private val mLoggingInteractor: LoggingInteractor,
    private val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers
): BaseMoxyPresenter<CitiesSelectionMoxyView>(), CitiesSelectionView {

    override fun providePresenter() = CitiesSelectionPresenter(mLoggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mRouter, mRxSchedulers) as BasePresenter<BaseView>

    private inline fun getPresenter() = mPresenter as CitiesSelectionPresenter

    fun moveBack() = getPresenter().moveBack()

    override fun setStartCityName(name: String) = viewState.setStartCityName(name)

    override fun setDestinationCityName(name: String) = viewState.setDestinationCityName(name)

    override fun setSearchButtonEnabled(isEnabled: Boolean) = viewState.setSearchButtonEnabled(isEnabled)

    fun onStartCityButtonClicks(clicksListener: Observable<Any>) {

        getPresenter().onStartCityButtonClicks(clicksListener)
    }

    fun onDestinationCityButtonClicks(clicksListener: Observable<Any>) {

        getPresenter().onDestinationCityButtonClicks(clicksListener)
    }

    fun onSearchCitiesButtonClicks(clicksListener: Observable<Any>) {

        getPresenter().onSearchCitiesButtonClicks(clicksListener)
    }
}