package com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view

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
import com.sedymov.aviasales.presentation.search.cityselection.startcityselection.presenter.StartCitySelectionMoxyPresenter
import kotlinx.android.synthetic.main.fragment_city_selection.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class StartCitySelectionFragment: BaseFragmentWithOnBackPressedListener(), StartCitySelectionMoxyView {

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    @Inject
    internal lateinit var mLoggingInteractor: LoggingInteractor

    @Inject
    internal lateinit var mMessagingInteractor: MessagingInteractor

    @Inject
    internal lateinit var mSearchCitiesInteractor: SearchCitiesInteractor

    @Inject
    internal lateinit var mRxSchedulers: RxSchedulers

    @Inject
    internal lateinit var mSearchRouter: SearchRouter

    @InjectPresenter
    internal lateinit var mPresenter: StartCitySelectionMoxyPresenter

    @ProvidePresenter
    internal fun providePresenter(): StartCitySelectionMoxyPresenter = StartCitySelectionMoxyPresenter(mLoggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mSearchRouter, mRxSchedulers)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_city_selection, container, false)

    override fun onResume() {
        super.onResume()

        mPresenter.onInputChanges(citySearch.getInputListener())
    }

    override fun onBackPressed(): Boolean {

        mPresenter.moveBack()
        return true
    }

    companion object {

        fun newInstance(): StartCitySelectionFragment {

            return StartCitySelectionFragment().apply {

                arguments = Bundle()
            }
        }
    }
}