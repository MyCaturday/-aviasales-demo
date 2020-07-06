package com.sedymov.aviasales.di.main

import com.sedymov.aviasales.core.presentation.main.navigation.MainRouter
import com.sedymov.aviasales.di.main.MainComponent.Companion.MAIN_SCOPE
import com.sedymov.aviasales.presentation.main.navigation.MainRouterImpl
import dagger.Lazy
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Named

@Module
class MainNavigationModule(private val mCicerone: Cicerone<Router> = Cicerone.create()) {

    @Provides
    @PerMain
    internal fun mainRouter(
        lazy: Lazy<MainRouterImpl>
    ): MainRouter = lazy.get()

    @Provides
    @PerMain
    internal fun mainRouterImpl(): MainRouterImpl =
        MainRouterImpl(mCicerone.router)

    @Provides
    @PerMain
    @Named(MAIN_SCOPE)
    internal fun navigatorHolder(): NavigatorHolder = mCicerone.navigatorHolder
}