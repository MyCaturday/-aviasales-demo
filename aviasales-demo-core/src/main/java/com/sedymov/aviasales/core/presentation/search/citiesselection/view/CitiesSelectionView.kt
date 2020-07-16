package com.sedymov.aviasales.core.presentation.search.citiesselection.view

import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.base.view.ViewWithErrorMessageSupport

interface CitiesSelectionView : BaseView, ViewWithErrorMessageSupport {

    fun setStartCityName(name: String)

    fun setDestinationCityName(name: String)

    fun setSearchButtonEnabled(isEnabled: Boolean)
}