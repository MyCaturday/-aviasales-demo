package com.sedymov.aviasales.di.app

import android.content.Context
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.interactors.ToastMessagingInteractor
import dagger.Module
import dagger.Provides


@Module
class MessagingModule {

    @Provides
    @PerApplication
    internal fun messagingInteractor(context: Context): MessagingInteractor =
        ToastMessagingInteractor(context)
}