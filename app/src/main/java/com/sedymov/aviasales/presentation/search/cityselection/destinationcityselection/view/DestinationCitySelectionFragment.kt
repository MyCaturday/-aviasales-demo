package com.sedymov.aviasales.presentation.search.cityselection.destinationcityselection.view

import android.os.Bundle
import com.sedymov.aviasales.core.presentation.search.cityselection.base.presenter.BaseCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.base.view.BaseCitySelectionView
import com.sedymov.aviasales.core.presentation.search.cityselection.destinationcityselection.presenter.DestinationCitySelectionPresenter
import com.sedymov.aviasales.core.presentation.search.cityselection.destinationcityselection.view.DestinationCitySelectionView
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class DestinationCitySelectionFragment: BaseCitySelectionFragment(), DestinationCitySelectionView {

    override val mPresenter: BaseCitySelectionPresenter<BaseCitySelectionView> by moxyPresenter { mPresenterProvider.get() as BaseCitySelectionPresenter<BaseCitySelectionView> }

    @Inject
    internal lateinit var mPresenterProvider: Provider<DestinationCitySelectionPresenter>

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    companion object {

        fun newInstance(): DestinationCitySelectionFragment {

            return DestinationCitySelectionFragment().apply {

                arguments = Bundle()
            }
        }
    }
}