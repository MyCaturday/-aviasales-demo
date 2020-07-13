package com.sedymov.aviasales.core.presentation.base

interface TimeInterpolator {

    fun getInterpolation(input: Float): Float
}