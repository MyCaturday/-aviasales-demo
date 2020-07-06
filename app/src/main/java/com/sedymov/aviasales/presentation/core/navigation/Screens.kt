package com.sedymov.aviasales.presentation.core.navigation

import com.sedymov.aviasales.presentation.search.container.view.SearchContainerFragment
import com.sedymov.aviasales.presentation.search.citiesselection.view.CitiesSelectionFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    class SearchScopeScreen : SupportAppScreen() {
        override fun getFragment() = SearchContainerFragment.newInstance()
    }

    class CitiesSelectionScreen : SupportAppScreen() {

        override fun getFragment() = CitiesSelectionFragment.newInstance()
    }
}