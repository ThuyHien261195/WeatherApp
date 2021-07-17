package com.media2359.weatherapp.content.network.response

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("coord")
    val coordinate: CoordinateResponse?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("population")
    val population: Long?,
    @SerializedName("timezone")
    val timezone: Long
) {
    data class CoordinateResponse(
        @SerializedName("lon")
        val lon: Double?,
        @SerializedName("lat")
        val lat: Double?
    )
}