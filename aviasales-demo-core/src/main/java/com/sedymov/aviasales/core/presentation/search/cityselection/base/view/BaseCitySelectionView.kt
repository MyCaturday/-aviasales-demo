package com.sedymov.aviasales.core.presentation.search.cityselection.base.view

import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.base.view.ViewWithErrorMessageSupport
import moxy.viewstate.strategy.alias.AddToEndSingle

interface BaseCitySelectionView : BaseView, ViewWithErrorMessageSupport {

    @AddToEndSingle
    fun showCities(cities: List<City>)

    @AddToEndSingle
    fun showLoading(show: Boolean)
}