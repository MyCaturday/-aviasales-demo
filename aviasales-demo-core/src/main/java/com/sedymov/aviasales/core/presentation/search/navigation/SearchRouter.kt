package com.sedymov.aviasales.core.presentation.search.navigation

import com.sedymov.aviasales.core.models.search.City

interface SearchRouter {

    fun moveBack()

    fun moveToCitiesSelection()

    fun moveToStartCitySelectionScreen()

    fun moveToDestinationCitySelectionScreen()

    fun moveToSearchResult(cities: Pair<City, City>)
}