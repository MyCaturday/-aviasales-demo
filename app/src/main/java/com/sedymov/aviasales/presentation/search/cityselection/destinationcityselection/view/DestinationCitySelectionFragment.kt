package com.sedymov.aviasales.presentation.search.cityselection.destinationcityselection.view

import android.os.Bundle
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.search.cityselection.base.presenter.BaseCitySelectionMoxyPresenter
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionFragment
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionMoxyView
import com.sedymov.aviasales.presentation.search.cityselection.destinationcityselection.presenter.DestinationCitySelectionMoxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class DestinationCitySelectionFragment: BaseCitySelectionFragment(), DestinationCitySelectionMoxyView {

    override val mPresenter: BaseCitySelectionMoxyPresenter<BaseCitySelectionMoxyView> by lazy { mDestinationCitySelectionMoxyPresenter as BaseCitySelectionMoxyPresenter<BaseCitySelectionMoxyView> }

    @InjectPresenter
    internal lateinit var mDestinationCitySelectionMoxyPresenter: DestinationCitySelectionMoxyPresenter

    @ProvidePresenter
    internal fun providePresenter(): DestinationCitySelectionMoxyPresenter = DestinationCitySelectionMoxyPresenter(mLoggingInteractor, mSearchCitiesInteractor, mSearchRouter, mRxSchedulers)

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    companion object {

        fun newInstance(): DestinationCitySelectionFragment {

            return DestinationCitySelectionFragment().apply {

                arguments = Bundle()
            }
        }
    }
}