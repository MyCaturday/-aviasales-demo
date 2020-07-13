package com.sedymov.aviasales.core.presentation.search.citiesselection.view

import com.sedymov.aviasales.core.presentation.base.view.BaseView

interface CitiesSelectionView : BaseView {

    fun setStartCityName(name: String)

    fun setDestinationCityName(name: String)

    fun setSearchButtonEnabled(isEnabled: Boolean)
}