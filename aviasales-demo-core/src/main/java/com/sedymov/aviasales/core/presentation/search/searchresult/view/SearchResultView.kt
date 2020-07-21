package com.sedymov.aviasales.core.presentation.search.searchresult.view

import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.base.view.ViewWithErrorMessageSupport
import moxy.viewstate.strategy.alias.AddToEndSingle

interface SearchResultView : BaseView, ViewWithErrorMessageSupport {

    @AddToEndSingle
    fun setMarkerAtStartCity(point: Pair<Double, Double>, name: String) // FYI: I don't like to use pairs here

    @AddToEndSingle
    fun setMarkerAtDestinationCity(point: Pair<Double, Double>, name: String)

    @AddToEndSingle
    fun drawLine(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>)

    @AddToEndSingle
    fun setPlaneMarker(point: Pair<Double, Double>)

    @AddToEndSingle
    fun setPlaneMarkerPosition(point: Pair<Double, Double>)

    @AddToEndSingle
    fun setPlaneMarkerRotation(rotation: Float)

    @AddToEndSingle
    fun setCameraAt(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>)

}