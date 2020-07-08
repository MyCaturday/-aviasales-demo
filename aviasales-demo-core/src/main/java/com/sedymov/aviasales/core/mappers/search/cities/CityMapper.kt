package com.sedymov.aviasales.core.mappers.search.cities

import com.sedymov.aviasales.core.models.search.CitiesResponse
import com.sedymov.aviasales.core.models.search.City

class CityMapper {

    fun toModels(response: CitiesResponse): List<City> =
        response.cities
}