package com.sedymov.aviasales.core.presentation.search.searchresult.view

import com.sedymov.aviasales.core.presentation.base.view.BaseView

interface SearchResultView : BaseView {

    fun setMarkerAtStartCity(point: Pair<Double, Double>, name: String) // FYI: I don't like to use pairs here

    fun setMarkerAtDestinationCity(point: Pair<Double, Double>, name: String)

    fun drawLine(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>)

    fun setPlaneMarker(point: Pair<Double, Double>)

    fun setPlaneMarkerPosition(point: Pair<Double, Double>)

    fun setPlaneMarkerRotation(rotation: Float)

    fun setCameraAt(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>)

}