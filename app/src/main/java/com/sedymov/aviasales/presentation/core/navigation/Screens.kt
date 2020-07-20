package com.sedymov.aviasales.presentation.core.navigation

import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.models.search.SearchCitiesUiModel
import com.sedymov.aviasales.presentation.search.container.view.SearchContainerFragment
import com.sedymov.aviasales.presentation.search.citiesselection.view.CitiesSelectionFragment
import com.sedymov.aviasales.presentation.search.cityselection.destinationcityselection.view.DestinationCitySelectionFragment
import com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view.StartCitySelectionFragment
import com.sedymov.aviasales.presentation.search.searchresult.view.SearchResultFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    class SearchScopeScreen : SupportAppScreen() {

        override fun getFragment() = SearchContainerFragment.newInstance()
    }

    class CitiesSelectionScreen : SupportAppScreen() {

        override fun getFragment() = CitiesSelectionFragment.newInstance()
    }

    class StartCitySelectionScreen : SupportAppScreen() {

        override fun getFragment() = StartCitySelectionFragment.newInstance()
    }

    class DestinationCitySelectionScreen : SupportAppScreen() {

        override fun getFragment() = DestinationCitySelectionFragment.newInstance()
    }

    class SearchResultScreen(private val cities: SearchCitiesUiModel) : SupportAppScreen() {

        override fun getFragment() = SearchResultFragment.newInstance(cities)
    }
}