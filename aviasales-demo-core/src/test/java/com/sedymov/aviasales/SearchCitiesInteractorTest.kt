package com.sedymov.aviasales

import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.repositories.search.cities.SearchCitiesRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SearchCitiesInteractorTest {

    private lateinit var mSearchCitiesRepository: SearchCitiesRepository
    private lateinit var mSearchCitiesInteractor: SearchCitiesInteractor

    @Before
    fun setUp() {

        mSearchCitiesRepository = Mockito.mock(SearchCitiesRepository::class.java)
        mSearchCitiesInteractor = SearchCitiesInteractor(mSearchCitiesRepository)
    }

    @Test(expected = Test.None::class)
    fun testPublishingStartCity() {

        val city = Mockito.mock(City::class.java)
        Mockito.`when`(city.city).thenReturn("Омск")

        val destinationCityObserver =  mSearchCitiesInteractor.onDestinationCitySelected().test()
        val startCityObserver =  mSearchCitiesInteractor.onStartCitySelected().test()

        mSearchCitiesInteractor.selectStartCity(city)

        startCityObserver.assertValue(city)
        startCityObserver.assertNoErrors()

        destinationCityObserver.assertNoValues()
        destinationCityObserver.assertNoErrors()
    }

    @Test(expected = Test.None::class)
    fun testPublishingDestinationCity() {

        val city = Mockito.mock(City::class.java)
        Mockito.`when`(city.city).thenReturn("Омск")

        val startCityObserver =  mSearchCitiesInteractor.onStartCitySelected().test()
        val destinationCityObserver =  mSearchCitiesInteractor.onDestinationCitySelected().test()

        mSearchCitiesInteractor.selectDestinationCity(city)

        destinationCityObserver.assertValue(city)
        destinationCityObserver.assertNoErrors()

        startCityObserver.assertNoValues()
        startCityObserver.assertNoErrors()
    }
}