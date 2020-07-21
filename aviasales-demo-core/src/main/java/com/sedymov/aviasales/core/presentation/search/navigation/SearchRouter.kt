package com.sedymov.aviasales.core.presentation.search.navigation

interface SearchRouter {

    fun moveBack()

    fun moveToCitiesSelection()

    fun moveToStartCitySelectionScreen()

    fun moveToDestinationCitySelectionScreen()

    fun moveToSearchResult()
}