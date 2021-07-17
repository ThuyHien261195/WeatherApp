package com.media2359.weatherapp.content.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = CityForecastJunction.TABLE_NAME,
    indices = [
        Index(CityForecastJunction.COL_CITY_ID),
        Index(CityForecastJunction.COL_FORECAST_ID)
    ],
    primaryKeys = [
        CityForecastJunction.COL_CITY_ID, CityForecastJunction.COL_FORECAST_ID
    ]
)
data class CityForecastJunction(
    @ColumnInfo(name = COL_CITY_ID)
    val cityId: Int,
    @ColumnInfo(name = COL_FORECAST_ID)
    val forecastId: String
) {
    companion object {
        const val TABLE_NAME = "city_forecast"

        const val COL_CITY_ID = "city_id"
        const val COL_FORECAST_ID = "forecast_id"
    }
}