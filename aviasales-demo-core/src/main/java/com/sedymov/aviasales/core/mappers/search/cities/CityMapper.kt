package com.sedymov.aviasales.core.mappers.search.cities

import com.sedymov.aviasales.core.models.search.*

class CityMapper {

    fun toModels(response: CitiesResponse): List<City> =
        response.cities

    fun toUiModel(city: City): CityUiModel =
        with(city) { CityUiModel(this.city, toUiModel(location), iata.getOrNull(0) ?: this.city) }

    private fun toUiModel(location: Location): LocationUiModel =
        with(location) { LocationUiModel(lat, lon) }

}