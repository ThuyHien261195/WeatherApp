package com.media2359.weatherapp.content.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = CityEntity.TABLE_NAME,
    indices = [
        Index(CityEntity.COL_ID)
    ]
)
data class CityEntity(
    @PrimaryKey
    @ColumnInfo(name = COL_ID)
    val id: Int,
    @ColumnInfo(name = COL_NAME)
    val name: String?,
    @ColumnInfo(name = COL_LAT)
    val lat: Double?,
    @ColumnInfo(name = COL_LON)
    val lon: Double?,
    @ColumnInfo(name = COL_COUNTRY)
    val country: String?,
    @ColumnInfo(name = COL_POPULATION)
    val population: Long?,
    @ColumnInfo(name = COL_TIMEZONE)
    val timezone: Long?,
    @ColumnInfo(name = COL_FORECAST_ID_LIST)
    var forecastIdList: List<String>?
) {
    companion object {
        const val TABLE_NAME = "city"

        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_LAT = "lat"
        const val COL_LON = "lon"
        const val COL_COUNTRY = "country"
        const val COL_POPULATION = "population"
        const val COL_TIMEZONE = "timezone"
        const val COL_FORECAST_ID_LIST = "forecast_id_list"
    }
}