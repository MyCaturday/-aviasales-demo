package com.sedymov.aviasales.presentation.search.cityselection.base.presenter

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.search.cityselection.base.presenter.BaseCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.base.view.BaseCitySelectionView
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.presenter.StartCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.view.StartCitySelectionView
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.presentation.base.presenter.BaseMoxyPresenter
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionMoxyView
import com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view.StartCitySelectionMoxyView
import io.reactivex.Observable
import moxy.InjectViewState

abstract class BaseCitySelectionMoxyPresenter<V : BaseCitySelectionMoxyView>(
    private val loggingInteractor: LoggingInteractor,
    private val mSearchCitiesInteractor: SearchCitiesInteractor,
    private val mMessagingInteractor: MessagingInteractor,
    private val mSearchRouter: SearchRouter,
    private val mRxSchedulers: RxSchedulers
): BaseMoxyPresenter<V>(), BaseCitySelectionView {

    override fun providePresenter(): BasePresenter<BaseView> = providePresenter(loggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mSearchRouter, mRxSchedulers)

    protected abstract fun providePresenter(loggingInteractor: LoggingInteractor,
                                            searchCitiesInteractor: SearchCitiesInteractor,
                                            messagingInteractor: MessagingInteractor,
                                            searchRouter: SearchRouter,
                                            rxSchedulers: RxSchedulers) : BasePresenter<BaseView>

    private inline fun getPresenter(): BaseCitySelectionPresenter<BaseCitySelectionView> =
        mPresenter as BaseCitySelectionPresenter<BaseCitySelectionView>

    override fun showCities(cities: List<City>) = viewState.showCities(cities)

    override fun showLoading(show: Boolean) = viewState.showLoading(show)

    fun moveBack() = getPresenter().moveBack()

    fun onInputChanges(findViewListener: Observable<String>) {

        getPresenter().onInputChanges(findViewListener)
    }

    fun onCitySelected(city: City) {

        getPresenter().onCitySelected(city)
    }
}