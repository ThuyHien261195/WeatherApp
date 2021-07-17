package com.media2359.weatherapp.ui.test_util

import mockwebserver3.MockResponse
import okio.buffer
import okio.source

fun MockResponse.setBodyFromFile(fileName: String): MockResponse {
    val inputStream = javaClass.classLoader!!.getResourceAsStream("$fileName")
    val source = inputStream.source().buffer()
    val body = source.readString(Charsets.UTF_8)
    return setBody(body)
}
