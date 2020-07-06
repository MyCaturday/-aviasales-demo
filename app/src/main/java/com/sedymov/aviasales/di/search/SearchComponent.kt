package com.sedymov.aviasales.di.search

import com.sedymov.aviasales.presentation.search.container.view.SearchContainerFragment
import com.sedymov.aviasales.presentation.search.citiesselection.view.CitiesSelectionFragment
import dagger.Subcomponent

@PerSearch
@Subcomponent(modules = [
    SearchModule::class,
    SearchNavigationModule::class
])
interface SearchComponent {

    companion object {

        const val SEARCH_SCOPE = "search_scope"
    }

    @Subcomponent.Builder
    interface Builder {
        fun build(): SearchComponent
    }

    fun inject(fragment: SearchContainerFragment)
    fun inject(fragment: CitiesSelectionFragment)
}