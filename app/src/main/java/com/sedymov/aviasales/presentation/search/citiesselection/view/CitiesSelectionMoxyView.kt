package com.sedymov.aviasales.presentation.search.citiesselection.view

import com.sedymov.aviasales.presentation.base.view.BaseMoxyView
import com.sedymov.aviasales.presentation.base.view.MoxyViewWithErrorMessageSupport
import moxy.viewstate.strategy.alias.AddToEndSingle

interface CitiesSelectionMoxyView : BaseMoxyView, MoxyViewWithErrorMessageSupport {

    @AddToEndSingle
    fun setStartCityName(name: String)

    @AddToEndSingle
    fun setDestinationCityName(name: String)

    @AddToEndSingle
    fun setSearchButtonEnabled(isEnabled: Boolean)
}