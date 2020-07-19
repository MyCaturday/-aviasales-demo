package com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view

import android.os.Bundle
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.search.cityselection.base.presenter.BaseCitySelectionMoxyPresenter
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionFragment
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionMoxyView
import com.sedymov.aviasales.presentation.search.cityselection.startcityselection.presenter.StartCitySelectionMoxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class StartCitySelectionFragment: BaseCitySelectionFragment(), StartCitySelectionMoxyView {

    override val mPresenter: BaseCitySelectionMoxyPresenter<BaseCitySelectionMoxyView> by lazy { mStartCitySelectionMoxyPresenter as BaseCitySelectionMoxyPresenter<BaseCitySelectionMoxyView> }

    @InjectPresenter
    internal lateinit var mStartCitySelectionMoxyPresenter: StartCitySelectionMoxyPresenter

    @ProvidePresenter
    internal fun providePresenter(): StartCitySelectionMoxyPresenter = StartCitySelectionMoxyPresenter(mLoggingInteractor, mSearchCitiesInteractor, mSearchRouter, mRxSchedulers)

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    companion object {

        fun newInstance(): StartCitySelectionFragment {

            return StartCitySelectionFragment().apply {

                arguments = Bundle()
            }
        }
    }
}