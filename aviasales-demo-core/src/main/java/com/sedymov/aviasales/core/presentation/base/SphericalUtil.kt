package com.sedymov.aviasales.core.presentation.base

interface SphericalUtil {

    fun interpolate(from: Pair<Double, Double>, to: Pair<Double, Double>, fraction: Double): Pair<Double, Double>

    fun computeHeading(from: Pair<Double, Double>, to: Pair<Double, Double>): Double
}