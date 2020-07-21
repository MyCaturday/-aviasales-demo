package com.sedymov.aviasales.presentation

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.mappers.search.cities.CityMapper
import com.sedymov.aviasales.core.presentation.search.citiesselection.presenter.CitiesSelectionPresenter
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.`CitiesSelectionView$$State`
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.repositories.search.citiesselection.CitiesSelectionResourcesRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CitiesSelectionPresenterTest {

    private lateinit var mPresenter: CitiesSelectionPresenter

    private lateinit var mViewState: `CitiesSelectionView$$State`

    private lateinit var loggingInteractor: LoggingInteractor
    private lateinit var mSearchCitiesInteractor: SearchCitiesInteractor
    private lateinit var mCityMapper: CityMapper
    private lateinit var mCitiesSelectionResourcesRepository: CitiesSelectionResourcesRepository
    private lateinit var mSearchRouter: SearchRouter
    private lateinit var mRxSchedulers: RxSchedulers

    @Before
    fun setUp() {

        loggingInteractor = Mockito.mock(LoggingInteractor::class.java)
        mSearchCitiesInteractor = Mockito.mock(SearchCitiesInteractor::class.java)

        Mockito.`when`(mSearchCitiesInteractor.onStartCitySelected()).thenReturn(Observable.empty())
        Mockito.`when`(mSearchCitiesInteractor.onDestinationCitySelected()).thenReturn(Observable.empty())

        mCityMapper = Mockito.mock(CityMapper::class.java)
        mCitiesSelectionResourcesRepository = Mockito.mock(CitiesSelectionResourcesRepository::class.java)
        mSearchRouter = Mockito.mock(SearchRouter::class.java)
        mRxSchedulers = Mockito.mock(RxSchedulers::class.java)

        Mockito.`when`(mRxSchedulers.ioScheduler).thenReturn(Schedulers.trampoline())
        Mockito.`when`(mRxSchedulers.mainThreadScheduler).thenReturn(Schedulers.trampoline())

        mPresenter = CitiesSelectionPresenter(
            loggingInteractor,
            mSearchCitiesInteractor,
            mCityMapper,
            mCitiesSelectionResourcesRepository,
            mSearchRouter,
            mRxSchedulers
        )

        mViewState = Mockito.mock(`CitiesSelectionView$$State`::class.java)

        mPresenter.attachView(mViewState)
    }

    @Test
    fun clickBack() {
        mPresenter.moveBack()
        Mockito.verify(mSearchRouter).moveBack()
    }
}