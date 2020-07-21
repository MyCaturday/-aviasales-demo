package com.sedymov.aviasales.di.search

import android.content.Context
import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.mappers.search.cities.CityMapper
import com.sedymov.aviasales.core.presentation.base.SphericalUtil
import com.sedymov.aviasales.core.presentation.base.TimeInterpolator
import com.sedymov.aviasales.core.presentation.search.citiesselection.presenter.CitiesSelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.destinationcityselection.presenter.DestinationCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.presenter.StartCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.container.presenter.SearchContainerPresenter
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.core.repositories.search.cities.SearchCitiesRepository
import com.sedymov.aviasales.core.repositories.search.citiesselection.CitiesSelectionResourcesRepository
import com.sedymov.aviasales.data.net.ApiClient
import com.sedymov.aviasales.presentation.base.GoogleMapsSphericalUtil
import com.sedymov.aviasales.presentation.base.LinearTimeInterpolator
import com.sedymov.aviasales.repositories.search.cities.SearchCitiesRepositoryImpl
import com.sedymov.aviasales.repositories.search.citiesselection.CitiesSelectionResourcesRepositoryImpl
import dagger.Module
import dagger.Provides
import moxy.presenter.ProvidePresenter

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
    internal fun citiesSelectionResourcesRepository(context: Context): CitiesSelectionResourcesRepository =
        CitiesSelectionResourcesRepositoryImpl(context)

    @Provides
    @PerSearch
    internal fun timeInterpolator(): TimeInterpolator = LinearTimeInterpolator()

    @Provides
    @PerSearch
    internal fun sphericalUtil(): SphericalUtil = GoogleMapsSphericalUtil()

    @Provides
    @PerSearch
    internal fun citiesSelectionPresenter(
        loggingInteractor: LoggingInteractor,
        searchCitiesInteractor: SearchCitiesInteractor,
        cityMapper: CityMapper,
        citiesSelectionResourcesRepository: CitiesSelectionResourcesRepository,
        searchRouter: SearchRouter,
        rxSchedulers: RxSchedulers
    ) = CitiesSelectionPresenter(loggingInteractor, searchCitiesInteractor, cityMapper, citiesSelectionResourcesRepository, searchRouter, rxSchedulers)

    @Provides
    @PerSearch
    internal fun destinationCitySelectionPresenter(
        loggingInteractor: LoggingInteractor,
        searchCitiesInteractor: SearchCitiesInteractor,
        searchRouter: SearchRouter,
        rxSchedulers: RxSchedulers
    ) = DestinationCitySelectionPresenter(loggingInteractor, searchCitiesInteractor, searchRouter, rxSchedulers)

    @Provides
    @PerSearch
    internal fun startCitySelectionPresenter(
        loggingInteractor: LoggingInteractor,
        searchCitiesInteractor: SearchCitiesInteractor,
        searchRouter: SearchRouter,
        rxSchedulers: RxSchedulers
    ) = StartCitySelectionPresenter(loggingInteractor, searchCitiesInteractor, searchRouter, rxSchedulers)

    @Provides
    @PerSearch
    internal fun searchContainerPresenter(
        loggingInteractor: LoggingInteractor,
        searchRouter: SearchRouter
    ) = SearchContainerPresenter(loggingInteractor, searchRouter)
}