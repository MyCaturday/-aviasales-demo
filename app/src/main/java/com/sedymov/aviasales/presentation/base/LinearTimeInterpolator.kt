package com.sedymov.aviasales.presentation.base

import android.view.animation.LinearInterpolator
import com.sedymov.aviasales.core.presentation.base.TimeInterpolator

class LinearTimeInterpolator() : TimeInterpolator {

    private val mInterpolator = LinearInterpolator()

    override fun getInterpolation(input: Float): Float =
        mInterpolator.getInterpolation(input)
}