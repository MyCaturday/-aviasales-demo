package com.sedymov.aviasales.core.presentation.search.cityselection.startcityselection.view

import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.view.BaseView

interface StartCitySelectionView : BaseView {

    fun showCities(cities: List<City>)

    fun showLoading(show: Boolean)
}