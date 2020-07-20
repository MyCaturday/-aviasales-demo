package com.sedymov.aviasales.core.presentation.search.citiesselection.view

import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.base.view.ViewWithErrorMessageSupport
import moxy.viewstate.strategy.alias.AddToEndSingle

interface CitiesSelectionView : BaseView, ViewWithErrorMessageSupport {

    @AddToEndSingle
    fun setStartCityName(name: String)

    @AddToEndSingle
    fun setDestinationCityName(name: String)

    @AddToEndSingle
    fun setSearchButtonEnabled(isEnabled: Boolean)
}