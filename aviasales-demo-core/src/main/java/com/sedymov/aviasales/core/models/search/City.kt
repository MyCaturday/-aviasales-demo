package com.sedymov.aviasales.core.models.search

data class City(

    val city: String,
    val location: Location,
    val iata: List<String>
)

data class Location(
    val lat: Double,
    val lon: Double
)