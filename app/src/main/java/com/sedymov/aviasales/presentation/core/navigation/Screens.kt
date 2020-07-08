package com.sedymov.aviasales.presentation.core.navigation

import com.sedymov.aviasales.presentation.search.container.view.SearchContainerFragment
import com.sedymov.aviasales.presentation.search.citiesselection.view.CitiesSelectionFragment
import com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view.StartCitySelectionFragment
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
}