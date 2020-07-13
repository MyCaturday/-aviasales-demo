package com.sedymov.aviasales.utils.platform

import com.google.android.gms.maps.model.LatLng

fun Pair<Double, Double>.toLatLng(): LatLng =
    with(this) { LatLng(first, second) }