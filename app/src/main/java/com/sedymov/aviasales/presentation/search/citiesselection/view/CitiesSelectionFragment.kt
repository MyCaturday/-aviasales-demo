package com.sedymov.aviasales.presentation.search.citiesselection.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener
import com.sedymov.aviasales.presentation.search.citiesselection.presenter.CitiesSelectionMoxyPresenter
import com.sedymov.aviasales.utils.platform.safeClickListener
import kotlinx.android.synthetic.main.fragment_cities_selection.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class CitiesSelectionFragment: BaseFragmentWithOnBackPressedListener(), CitiesSelectionMoxyView {

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
    internal lateinit var mPresenter: CitiesSelectionMoxyPresenter

    @ProvidePresenter
    internal fun providePresenter(): CitiesSelectionMoxyPresenter = CitiesSelectionMoxyPresenter(mLoggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mSearchRouter, mRxSchedulers)

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    override fun setStartCityName(name: String) {

        startCityButton.text = name
    }

    override fun setDestinationCityName(name: String) {

        destinationCityButton.text = name
    }

    override fun setSearchButtonEnabled(isEnabled: Boolean) {

        searchCitiesButton.isEnabled = isEnabled
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_cities_selection, container, false)

    override fun onResume() {
        super.onResume()

        mPresenter.onStartCityButtonClicks(startCityButton.safeClickListener())
        mPresenter.onDestinationCityButtonClicks(destinationCityButton.safeClickListener())
        mPresenter.onSearchCitiesButtonClicks(searchCitiesButton.safeClickListener())
    }

    override fun onBackPressed(): Boolean {

        mPresenter.moveBack()
        return true
    }

    companion object {

        fun newInstance(): CitiesSelectionFragment {

            return CitiesSelectionFragment().apply {

                arguments = Bundle()
            }
        }
    }
}