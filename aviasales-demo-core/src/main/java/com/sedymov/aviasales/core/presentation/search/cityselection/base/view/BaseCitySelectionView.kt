package com.sedymov.aviasales.core.presentation.search.cityselection.base.view

import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.base.view.ViewWithErrorMessageSupport

interface BaseCitySelectionView : BaseView, ViewWithErrorMessageSupport {

    fun showCities(cities: List<City>)

    fun showLoading(show: Boolean)
}