package com.sedymov.aviasales.presentation.search.citiesselection.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.presentation.search.citiesselection.presenter.CitiesSelectionPresenter
import com.sedymov.aviasales.core.presentation.search.citiesselection.view.CitiesSelectionView
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithErrorMessageSupport
import com.sedymov.aviasales.utils.platform.safeClickListener
import kotlinx.android.synthetic.main.fragment_cities_selection.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class CitiesSelectionFragment: BaseFragmentWithErrorMessageSupport(), CitiesSelectionView {

    @Inject
    internal lateinit var mPresenterProvider: Provider<CitiesSelectionPresenter>

    private val mPresenter: CitiesSelectionPresenter by moxyPresenter { mPresenterProvider.get() }

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