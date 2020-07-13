package com.sedymov.aviasales.di.app

import android.content.Context
import com.sedymov.aviasales.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @PerApplication
    internal fun getAppContext(): Context = App.instance
}