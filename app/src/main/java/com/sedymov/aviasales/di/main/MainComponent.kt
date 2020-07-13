package com.sedymov.aviasales.di.main

import com.sedymov.aviasales.presentation.main.view.MainActivity
import dagger.Subcomponent

@PerMain
@Subcomponent(modules = [
    MainModule::class,
    MainNavigationModule::class
])
interface MainComponent {

    companion object {

        const val MAIN_SCOPE = "main_scope"
    }

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }

    fun inject(activity: MainActivity)
}