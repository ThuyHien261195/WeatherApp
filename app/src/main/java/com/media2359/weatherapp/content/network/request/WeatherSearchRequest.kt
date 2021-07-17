package com.media2359.weatherapp.content.network.request

import com.media2359.weatherapp.BuildConfig.APPLICATION_ID
import com.media2359.weatherapp.utils.MEASURE_UNITS
import com.media2359.weatherapp.utils.NUMBER_DAYS

data class WeatherSearchRequest(
    val cityName: String,
    val numberDays: Int = NUMBER_DAYS,
    val appId: String = APPLICATION_ID,
    val units: String = MEASURE_UNITS
)