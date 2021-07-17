package com.media2359.weatherapp.content.model.wrapper

sealed class Resource<out T> {
    data class Success<out T>(val data: T): Resource<T>()
    data class Error<out T>(val error: Throwable, val data: T? = null): Resource<T>()
    data class Loading<out T>(val data: T? = null): Resource<T>()

    fun getAvailableData(): T? {
        return (this as? Success)?.data
            ?: (this as? Error)?.data
            ?: (this as? Loading)?.data
    }
}