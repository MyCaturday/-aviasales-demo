package com.sedymov.aviasales.presentation.search.cityselection.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.utils.platform.checkPositionAndRun
import com.sedymov.aviasales.utils.platform.inflate

typealias CityOnClickListener = (city: City) -> Unit

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal val cityNameTv: TextView = itemView.findViewById(R.id.cityNameTextView)
}

class CityAdapter(
    private val context: Context,
    private val cities: List<City>,
    private val itemClickListener: CityOnClickListener
) : RecyclerView.Adapter<CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {

        val view = parent.inflate(R.layout.city_item)

        return CityViewHolder(view).apply {

            itemView.setOnClickListener {

                checkPositionAndRun { position ->

                    itemClickListener.invoke(cities[position])
                }
            }
        }
    }

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {

        cities[position].let { city ->

            with(holder) {

                cityNameTv.text = city.city
            }
        }
    }
}