package com.sedymov.aviasales.repositories.search.citiesselection

import android.content.Context
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.repositories.search.citiesselection.CitiesSelectionResourcesRepository

class CitiesSelectionResourcesRepositoryImpl(private val mContext: Context): CitiesSelectionResourcesRepository {

    override fun citiesAreIdenticalErrorText() = mContext.getString(R.string.cities_are_identical_error_text)
}