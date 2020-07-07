package com.sedymov.aviasales.presentation.search.citiesselection.view

import com.sedymov.aviasales.presentation.base.view.BaseMoxyViewWithTitle
import moxy.viewstate.strategy.alias.AddToEndSingle

interface CitiesSelectionMoxyView : BaseMoxyViewWithTitle {

    @AddToEndSingle
    fun setSearchButtonEnabled(isEnabled: Boolean)
}