package com.sedymov.aviasales.presentation.search.cityselection.startcityselection.view

import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.presentation.base.view.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface StartCitySelectionMoxyView : BaseMoxyView {

    @AddToEndSingle
    fun showCities(cities: List<City>)

    @AddToEndSingle
    fun showLoading(show: Boolean)
}