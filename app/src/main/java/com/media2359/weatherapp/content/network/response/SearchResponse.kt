package com.media2359.weatherapp.content.network.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("city")
    val city: CityResponse,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("list")
    val forecastList: List<ForecastResponse>?
) : BaseResponse()