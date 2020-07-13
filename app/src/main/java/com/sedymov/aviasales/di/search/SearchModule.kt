package com.sedymov.aviasales.di.search

import android.view.animation.LinearInterpolator
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.mappers.search.cities.CityMapper
import com.sedymov.aviasales.core.presentation.base.SphericalUtil
import com.sedymov.aviasales.core.presentation.base.TimeInterpolator
import com.sedymov.aviasales.core.repositories.search.cities.SearchCitiesRepository
import com.sedymov.aviasales.data.net.ApiClient
import com.sedymov.aviasales.presentation.base.GoogleMapsSphericalUtil
import com.sedymov.aviasales.presentation.base.LinearTimeInterpolator
import com.sedymov.aviasales.repositories.search.cities.SearchCitiesRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    @PerSearch
    internal fun cityMapper(): CityMapper = CityMapper()

    @Provides
    @PerSearch
    internal fun searchCitiesRepository(apiCLient: ApiClient, cityMapper: CityMapper): SearchCitiesRepository =
        SearchCitiesRepositoryImpl(apiCLient, cityMapper)

    @Provides
    @PerSearch
    internal fun searchCityInteractor(searchCitiesRepository: SearchCitiesRepository): SearchCitiesInteractor =
        SearchCitiesInteractor(
            searchCitiesRepository
        )

    @Provides
    @PerSearch
    internal fun timeInterpolator(): TimeInterpolator = LinearTimeInterpolator()

    @Provides
    @PerSearch
    internal fun sphericalUtil(): SphericalUtil = GoogleMapsSphericalUtil()
}