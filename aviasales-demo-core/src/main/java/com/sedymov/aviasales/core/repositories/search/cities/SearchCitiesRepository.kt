package com.sedymov.aviasales.core.repositories.search.cities

import com.sedymov.aviasales.core.models.search.City
import io.reactivex.Single

interface SearchCitiesRepository {

    fun getCities(mask: String): Single<List<City>>
}