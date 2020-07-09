package com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.core.view.postDelayed
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener
import com.sedymov.aviasales.presentation.search.cityselection.adapters.CityAdapter
import com.sedymov.aviasales.presentation.search.cityselection.base.presenter.BaseCitySelectionMoxyPresenter
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionFragment
import com.sedymov.aviasales.presentation.search.cityselection.base.view.BaseCitySelectionMoxyView
import com.sedymov.aviasales.presentation.search.cityselection.startcityselection.presenter.StartCitySelectionMoxyPresenter
import com.sedymov.aviasales.utils.platform.SEARCH_TIMER_DELAY_MILLISECONDS
import com.sedymov.aviasales.utils.platform.setAsAdapterFor
import com.sedymov.aviasales.utils.platform.setGone
import com.sedymov.aviasales.utils.platform.setVisible
import kotlinx.android.synthetic.main.fragment_city_selection.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class StartCitySelectionFragment: BaseCitySelectionFragment(), StartCitySelectionMoxyView {

    override val mPresenter: BaseCitySelectionMoxyPresenter<BaseCitySelectionMoxyView> by lazy { mStartCitySelectionMoxyPresenter as BaseCitySelectionMoxyPresenter<BaseCitySelectionMoxyView> }

    @InjectPresenter
    internal lateinit var mStartCitySelectionMoxyPresenter: StartCitySelectionMoxyPresenter

    @ProvidePresenter
    internal fun providePresenter(): StartCitySelectionMoxyPresenter = StartCitySelectionMoxyPresenter(mLoggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mSearchRouter, mRxSchedulers)

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    companion object {

        fun newInstance(): StartCitySelectionFragment {

            return StartCitySelectionFragment().apply {

                arguments = Bundle()
            }
        }
    }
}