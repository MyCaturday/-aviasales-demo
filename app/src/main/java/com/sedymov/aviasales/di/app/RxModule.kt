package com.sedymov.aviasales.di.app

import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.executors.AndroidSchedulers
import dagger.Module
import dagger.Provides

@Module
class RxModule {

    @Provides
    @PerApplication
    internal fun rxSchedulers(): RxSchedulers = AndroidSchedulers()
}