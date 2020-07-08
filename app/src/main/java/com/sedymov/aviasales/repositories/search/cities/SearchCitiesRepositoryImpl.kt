package com.sedymov.aviasales.repositories.search.cities

import com.sedymov.aviasales.core.mappers.search.cities.CityMapper
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.repositories.search.cities.SearchCitiesRepository
import com.sedymov.aviasales.data.net.ApiClient
import io.reactivex.Single

class SearchCitiesRepositoryImpl(
    private val apiClient: ApiClient,
    private val cityMapper: CityMapper
) : SearchCitiesRepository {

    override fun getCities(mask: String): Single<List<City>> {

        return apiClient.getCheckupByObjectId(mask)
            .map { cityMapper.toModels(it) }
    }
}