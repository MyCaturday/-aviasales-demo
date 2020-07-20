package com.sedymov.aviasales.core.models.search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class LocationUiModel(
    val latitude: Double,
    val longitude: Double
) : Parcelable