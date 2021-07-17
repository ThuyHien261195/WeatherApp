package com.media2359.weatherapp.content.model

data class Forecast(
    val id: String,
    val date: String? = null,
    val aveTemp: Double? = 0.0,
    val pressure: Int? = null,
    val humidity: Int? = null,
    val description: String? = null
)
