package com.sedymov.aviasales.presentation.search.navigation

import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.presentation.core.navigation.Screens
import ru.terrakok.cicerone.Router

class SearchRouterImpl(private val router: Router): SearchRouter {

    override fun moveBack() = router.exit()

    override fun moveToCitiesSelection() = router.newRootScreen(Screens.CitiesSelectionScreen())

    override fun moveToStartCitySelectionScreen() = router.navigateTo(Screens.StartCitySelectionScreen())

    override fun moveToDestinationCitySelectionScreen() = router.navigateTo(Screens.DestinationCitySelectionScreen())

    override fun moveToSearchResult() = router.navigateTo(Screens.SearchResultScreen())
}