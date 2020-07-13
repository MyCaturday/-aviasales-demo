package com.sedymov.aviasales.core.presentation.search.searchresult.view

import com.sedymov.aviasales.core.presentation.base.view.BaseView

interface SearchResultView : BaseView {

    fun setMarkerAtStartCity(lat: Double, lon: Double, name: String)

    fun setMarkerAtDestinationCity(lat: Double, lon: Double, name: String)

    fun drawLine(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>)

    fun setPlaneMarker(lat: Double, lon: Double)

    fun setPlaneMarkerPosition(lat: Double, lon: Double)

    fun setPlaneMarkerRotation(rotation: Float)

    fun setCameraAt(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>)

}