package com.media2359.weatherapp.content.network

import com.media2359.weatherapp.content.network.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast/daily")
    suspend fun getWeatherForecastList(
        @Query("q") cityName: String,
        @Query("cnt") numberDays: Int,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): SearchResponse
}