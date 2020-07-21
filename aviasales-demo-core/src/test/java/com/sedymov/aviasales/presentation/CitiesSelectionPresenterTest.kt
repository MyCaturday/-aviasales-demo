package com.sedymov.aviasales.presentation

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.mappers.search.cities.CityMapper
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.search.citiesselection.presenter.CitiesSelectionPresenter
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.`CitiesSelectionView$$State`
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.repositories.search.citiesselection.CitiesSelectionResourcesRepository
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
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

    private lateinit var mStartCitySubject: Subject<City>
    private lateinit var mDestinationCitySubject: Subject<City>

    @Before
    fun setUp() {

        loggingInteractor = Mockito.mock(LoggingInteractor::class.java)
        mSearchCitiesInteractor = Mockito.mock(SearchCitiesInteractor::class.java)

        mStartCitySubject = BehaviorSubject.create<City>()
        mDestinationCitySubject = BehaviorSubject.create<City>()

        Mockito.`when`(mSearchCitiesInteractor.onStartCitySelected()).thenReturn(mStartCitySubject)
        Mockito.`when`(mSearchCitiesInteractor.onDestinationCitySelected()).thenReturn(mDestinationCitySubject)

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

    @Test
    fun testMovingToStartCitySelection() {

        val buttonClickSubject = PublishSubject.create<Any>()
        mPresenter.onStartCityButtonClicks(buttonClickSubject)
        buttonClickSubject.onNext(Any())

        Mockito.verify(mSearchRouter).moveToStartCitySelectionScreen()
    }

    @Test
    fun testMovingToDestinationCitySelection() {

        val buttonClickSubject = PublishSubject.create<Any>()
        mPresenter.onDestinationCityButtonClicks(buttonClickSubject)
        buttonClickSubject.onNext(Any())

        Mockito.verify(mSearchRouter).moveToDestinationCitySelectionScreen()
    }

    @Test
    fun testSearchButtonTrue() {

        val startCityMock = Mockito.mock(City::class.java)
        Mockito.`when`(startCityMock.city).thenReturn("Омск")

        val destinationCityMock = Mockito.mock(City::class.java)
        Mockito.`when`(destinationCityMock.city).thenReturn("Москва")

        mStartCitySubject.onNext(startCityMock)
        mDestinationCitySubject.onNext(destinationCityMock)

        Mockito.verify(mViewState).setSearchButtonEnabled(true)
    }

    @Test
    fun testSearchButtonFalse() {

        val startCityMock = Mockito.mock(City::class.java)
        Mockito.`when`(startCityMock.city).thenReturn("Омск")

        mStartCitySubject.onNext(startCityMock)
        mDestinationCitySubject.onNext(startCityMock)

        Mockito.verify(mViewState).setSearchButtonEnabled(false)
    }
}