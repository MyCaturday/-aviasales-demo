package com.sedymov.aviasales.presentation.search.citiesselection.view

import com.sedymov.aviasales.presentation.base.view.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface CitiesSelectionMoxyView : BaseMoxyView {

    @AddToEndSingle
    fun setStartCityName(name: String)

    @AddToEndSingle
    fun setDestinationCityName(name: String)

    @AddToEndSingle
    fun setSearchButtonEnabled(isEnabled: Boolean)
}