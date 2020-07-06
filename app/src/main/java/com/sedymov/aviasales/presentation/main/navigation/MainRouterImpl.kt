package com.sedymov.aviasales.presentation.main.navigation

import com.sedymov.aviasales.core.presentation.main.navigation.MainRouter
import com.sedymov.aviasales.presentation.core.navigation.Screens
import ru.terrakok.cicerone.Router

class MainRouterImpl(private val router: Router): MainRouter {

    override fun moveToSearchScreen() = router.newRootScreen(Screens.SearchScopeScreen())
}