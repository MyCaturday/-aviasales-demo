package com.sedymov.aviasales.di.main

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.main.navigation.MainRouter
import com.sedymov.aviasales.core.presentation.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @PerMain
    internal fun mainPresenter(loggingInteractor: LoggingInteractor, mainRouter: MainRouter) = MainPresenter(loggingInteractor, mainRouter)
}