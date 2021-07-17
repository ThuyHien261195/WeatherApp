package com.media2359.weatherapp.content.model

data class City(
    val id: Int,
    val name: String? = null,
    val forecastList: List<Forecast>? = emptyList()
)