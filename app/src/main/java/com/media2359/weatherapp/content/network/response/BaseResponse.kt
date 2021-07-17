package com.media2359.weatherapp.content.network.response

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("message")
    val message: Any? = ""
)