package com.sedymov.aviasales.di.app

import com.sedymov.aviasales.di.main.MainComponent
import com.sedymov.aviasales.di.search.SearchComponent
import dagger.Component

@PerApplication
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    LoggingModule::class,
    RxModule::class
])
interface AppComponent {

    fun mainComponentBuilder(): MainComponent.Builder

    fun searchComponentBuilder(): SearchComponent.Builder
}