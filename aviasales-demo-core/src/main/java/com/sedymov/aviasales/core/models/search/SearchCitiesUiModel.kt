package com.sedymov.aviasales.core.models.search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SearchCitiesUiModel(
    val startCity: CityUiModel,
    val destinationCity: CityUiModel
) : Parcelable