package com.sedymov.aviasales.presentation.base

import com.google.android.gms.maps.model.LatLng
import com.sedymov.aviasales.core.presentation.base.SphericalUtil

class GoogleMapsSphericalUtil : SphericalUtil {

    override fun interpolate(
        from: Pair<Double, Double>,
        to: Pair<Double, Double>,
        fraction: Double
    ): Pair<Double, Double> {
        val result = com.google.maps.android.SphericalUtil.interpolate(LatLng(from.first, from.second), LatLng(to.first, to.second), fraction)
        return Pair(result.latitude, result.longitude)
    }

    override fun computeHeading(from: Pair<Double, Double>, to: Pair<Double, Double>): Double {

        val sourceLatLng = LatLng(from.first, from.second)
        val destinationLatLng = LatLng(to.first, to.second)
        return com.google.maps.android.SphericalUtil.computeHeading(sourceLatLng, destinationLatLng)
    }
}