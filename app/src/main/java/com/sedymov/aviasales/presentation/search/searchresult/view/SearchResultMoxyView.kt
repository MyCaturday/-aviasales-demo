package com.sedymov.aviasales.presentation.search.searchresult.view

import com.sedymov.aviasales.presentation.base.view.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface SearchResultMoxyView : BaseMoxyView {

    @AddToEndSingle
    fun setMarkerAtStartCity(point: Pair<Double, Double>, name: String)

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