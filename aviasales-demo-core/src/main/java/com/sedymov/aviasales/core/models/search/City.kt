package com.sedymov.aviasales.core.models.search

data class City(

    val city: String,
    val location: Location,
    val iata: List<String>
)

