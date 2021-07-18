package com.media2359.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Long.convertLongToDateTime(): String {
    val date = Date(TimeUnit.SECONDS.toMillis(this))
    val format = SimpleDateFormat("EEE, dd MMM yyyy")
    return format.format(date)
}