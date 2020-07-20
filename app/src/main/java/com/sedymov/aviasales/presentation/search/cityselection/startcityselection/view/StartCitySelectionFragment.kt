package com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view

import android.os.Bundle
import com.sedymov.aviasales.core.presentation.search.cityselection.base.presenter.BaseCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.base.view.BaseCitySelectionView
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.presenter.StartCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.view.StartCitySelectionView
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class StartCitySelectionFragment: BaseCitySelectionFragment(), StartCitySelectionView {

    override val mPresenter: BaseCitySelectionPresenter<BaseCitySelectionView> by lazy { mStartCitySelectionMoxyPresenter as BaseCitySelectionPresenter<BaseCitySelectionView> }

    @InjectPresenter
    internal lateinit var mStartCitySelectionMoxyPresenter: StartCitySelectionPresenter

    @ProvidePresenter
    internal fun providePresenter(): StartCitySelectionPresenter = StartCitySelectionPresenter(mLoggingInteractor, mSearchCitiesInteractor, mSearchRouter, mRxSchedulers)

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    companion object {

        fun newInstance(): StartCitySelectionFragment {

            return StartCitySelectionFragment().apply {

                arguments = Bundle()
            }
        }
    }
}