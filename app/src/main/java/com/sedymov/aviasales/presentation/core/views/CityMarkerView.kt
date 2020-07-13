package com.sedymov.aviasales.presentation.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.sedymov.aviasales.R

class CityMarkerView {

    lateinit var rootView: View
    private lateinit var cityNameTextView: TextView

    constructor(context: Context){

        init(context)
    }

    private fun init(context: Context, attrs: AttributeSet? = null) {

        rootView = View.inflate(context, R.layout.city_marker_layout, null).apply {

            cityNameTextView = findViewById(R.id.cityNameTextView)
        }
    }

    fun setCityName(name: String) {

        cityNameTextView.text = name
    }
}