package com.sedymov.aviasales.core.interactors.search.cities

import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.repositories.search.cities.SearchCitiesRepository
import io.reactivex.Single

class SearchCitiesInteractor(
    private val searchCitiesRepository: SearchCitiesRepository
) {

    fun getCities(mask: String): Single<List<City>> {

        return searchCitiesRepository.getCities(mask)
    }
}