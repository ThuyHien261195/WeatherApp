package com.media2359.weatherapp.content.database.entities

import android.text.style.ForegroundColorSpan
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = ForecastWeatherJunction.TABLE_NAME,
    indices = [
        Index(ForecastWeatherJunction.COL_FORECAST_ID),
        Index(ForecastWeatherJunction.COL_WEATHER_ID)
    ],
    primaryKeys = [
        ForecastWeatherJunction.COL_FORECAST_ID, ForecastWeatherJunction.COL_WEATHER_ID
    ]
)
data class ForecastWeatherJunction(
    @ColumnInfo(name = COL_FORECAST_ID)
    val forecastId: String,
    @ColumnInfo(name = COL_WEATHER_ID)
    val weatherId: Int
) {
    companion object {
        const val TABLE_NAME = "forecast_weather"

        const val COL_FORECAST_ID = "forecast_id"
        const val COL_WEATHER_ID = "weather_id"
    }
}