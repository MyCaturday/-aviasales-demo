package com.sedymov.aviasales.di.app

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.interactors.logging.AndroidDebugLoggingInteractor
import dagger.Module
import dagger.Provides

@Module
class LoggingModule {

    @Provides
    @PerApplication
    internal fun loggingInteractor(): LoggingInteractor =
        AndroidDebugLoggingInteractor()
}