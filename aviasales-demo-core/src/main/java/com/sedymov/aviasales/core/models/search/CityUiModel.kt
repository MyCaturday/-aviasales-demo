package com.sedymov.aviasales.core.models.search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CityUiModel(
    val name: String,
    val location: LocationUiModel,
    val airportName: String
) : Parcelable