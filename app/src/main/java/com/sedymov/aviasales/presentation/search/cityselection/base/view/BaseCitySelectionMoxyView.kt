package com.sedymov.aviasales.presentation.search.cityselection.base.view

import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.presentation.base.view.BaseMoxyView
import com.sedymov.aviasales.presentation.base.view.MoxyViewWithErrorMessageSupport
import moxy.viewstate.strategy.alias.AddToEndSingle

interface BaseCitySelectionMoxyView : BaseMoxyView, MoxyViewWithErrorMessageSupport {

    @AddToEndSingle
    fun showCities(cities: List<City>)

    @AddToEndSingle
    fun showLoading(show: Boolean)
}