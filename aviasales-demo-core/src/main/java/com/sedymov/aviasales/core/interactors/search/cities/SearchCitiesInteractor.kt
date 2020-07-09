package com.sedymov.aviasales.core.interactors.search.cities

import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.repositories.search.cities.SearchCitiesRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

class SearchCitiesInteractor(
    private val searchCitiesRepository: SearchCitiesRepository
) {

    private val startCitySubject: Subject<City> by lazy { BehaviorSubject.create<City>() }

    private val destinationCitySubject: Subject<City> by lazy { BehaviorSubject.create<City>() }

    fun getCities(mask: String): Single<List<City>> {

        return searchCitiesRepository.getCities(mask)
    }

    fun onStartCitySelected(): Observable<City> = startCitySubject

    fun selectStartCity(city: City) = startCitySubject.onNext(city)

    fun onDestinationCitySelected(): Observable<City> = destinationCitySubject

    fun selectDestinationCity(city: City) = destinationCitySubject.onNext(city)
}