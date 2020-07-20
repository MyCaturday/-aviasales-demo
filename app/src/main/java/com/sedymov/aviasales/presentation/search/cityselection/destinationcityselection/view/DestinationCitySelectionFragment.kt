package com.sedymov.aviasales.presentation.search.cityselection.destinationcityselection.view

import android.os.Bundle
import com.sedymov.aviasales.core.presentation.search.cityselection.base.presenter.BaseCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.base.view.BaseCitySelectionView
import com.sedymov.aviasales.core.presentation.search.cityselection.destinationcityselection.presenter.DestinationCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.destinationcityselection.view.DestinationCitySelectionView
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class DestinationCitySelectionFragment: BaseCitySelectionFragment(), DestinationCitySelectionView {

    override val mPresenter: BaseCitySelectionPresenter<BaseCitySelectionView> by lazy { mDestinationCitySelectionMoxyPresenter as BaseCitySelectionPresenter<BaseCitySelectionView> }

    @InjectPresenter
    internal lateinit var mDestinationCitySelectionMoxyPresenter: DestinationCitySelectionPresenter

    @ProvidePresenter
    internal fun providePresenter(): DestinationCitySelectionPresenter = DestinationCitySelectionPresenter(mLoggingInteractor, mSearchCitiesInteractor, mSearchRouter, mRxSchedulers)

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    companion object {

        fun newInstance(): DestinationCitySelectionFragment {

            return DestinationCitySelectionFragment().apply {

                arguments = Bundle()
            }
        }
    }
}