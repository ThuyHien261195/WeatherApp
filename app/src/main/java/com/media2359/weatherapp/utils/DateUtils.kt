package com.media2359.weatherapp.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun tickerFlow(delayMillis: Long, initialDelayMillis: Long = 0): Flow<Long> = flow {
    delay(initialDelayMillis)

    while (true) {
        emit(System.currentTimeMillis())
        delay(delayMillis)
    }
}

fun Long.convertLongToDateTime(): String {
    val date = Date(TimeUnit.SECONDS.toMillis(this))
    val format = SimpleDateFormat("EEE, dd MMM yyyy")
    return format.format(date)
}