package com.sedymov.aviasales.presentation.search.searchresult.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener
import com.sedymov.aviasales.presentation.search.citiesselection.view.CitiesSelectionFragment
import com.sedymov.aviasales.presentation.search.searchresult.presenter.SearchResultMoxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class SearchResultFragment : BaseFragmentWithOnBackPressedListener(), SearchResultMoxyView {

    @Inject
    internal lateinit var mLoggingInteractor: LoggingInteractor

    @Inject
    internal lateinit var mSearchCitiesInteractor: SearchCitiesInteractor

    @Inject
    internal lateinit var mMessagingInteractor: MessagingInteractor

    @Inject
    internal lateinit var mSearchRouter: SearchRouter

    @Inject
    internal lateinit var mRxSchedulers: RxSchedulers

    @InjectPresenter
    internal lateinit var mPresenter: SearchResultMoxyPresenter

    @ProvidePresenter
    internal fun providePresenter(): SearchResultMoxyPresenter = SearchResultMoxyPresenter(mLoggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mSearchRouter, mRxSchedulers)

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_search_result, container, false)

    companion object {

        fun newInstance(cities: Pair<City, City>): SearchResultFragment {

            return SearchResultFragment().apply {

                arguments = Bundle().apply {

                    putSerializable(CITIES_EXTRA, cities)
                }
            }
        }

        private const val CITIES_EXTRA = "com.sedymov.aviasales.CITIES_EXTRA"
    }
}