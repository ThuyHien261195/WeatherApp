package com.media2359.weatherapp.content.network.response

import com.google.gson.annotations.SerializedName

class ForecastResponse(
    @SerializedName("dt")
    val date: Long?,
    @SerializedName("sunrise")
    val sunrise: Long?,
    @SerializedName("sunset")
    val sunset: Long?,
    @SerializedName("temp")
    val temp: TemperatureResponse?,
    @SerializedName("feels_like")
    val feelsLike: TemperatureResponse?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("weather")
    val weatherList: List<WeatherResponse>?,
    @SerializedName("speed")
    val speed: Double?,
    @SerializedName("deg")
    val deg: Double?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("clouds")
    val clouds: Double?,
    @SerializedName("pop")
    val pop: Double?,
    @SerializedName("rain")
    val rain: Double?
) {
    data class TemperatureResponse(
        @SerializedName("day")
        val day: Double?,
        @SerializedName("min")
        val min: Double?,
        @SerializedName("max")
        val max: Double?,
        @SerializedName("night")
        val night: Double?,
        @SerializedName("eve")
        val eve: Double?,
        @SerializedName("morn")
        val morn: Double?
    )

    data class WeatherResponse(
        @SerializedName("id")
        val id: Int,
        @SerializedName("main")
        val main: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("icon")
        val icon: String?,
    )
}