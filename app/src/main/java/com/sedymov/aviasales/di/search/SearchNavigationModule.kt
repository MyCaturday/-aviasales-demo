package com.sedymov.aviasales.di.search

import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.presentation.search.navigation.SearchRouterImpl
import dagger.Lazy
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Named

@Module
class SearchNavigationModule(private val mCicerone: Cicerone<Router> = Cicerone.create()) {

    @Provides
    @PerSearch
    internal fun searchRouter(
        lazy: Lazy<SearchRouterImpl>
    ): SearchRouter = lazy.get()

    @Provides
    @PerSearch
    internal fun searchRouterImpl(): SearchRouterImpl =
        SearchRouterImpl(mCicerone.router)

    @Provides
    @PerSearch
    @Named(SearchComponent.SEARCH_SCOPE)
    internal fun navigatorHolder(): NavigatorHolder = mCicerone.navigatorHolder
}