package com.sedymov.aviasales.di.app

import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.interactors.AlerterMessagingInteractor
import dagger.Module
import dagger.Provides


@Module
class MessagingModule {

    @Provides
    @PerApplication
    internal fun messagingInteractor(): MessagingInteractor =
        AlerterMessagingInteractor()
}