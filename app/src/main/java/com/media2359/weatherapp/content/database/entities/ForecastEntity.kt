package com.media2359.weatherapp.content.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = ForecastEntity.TABLE_NAME,
    indices = [
        Index(ForecastEntity.COL_ID)
    ],
)
data class ForecastEntity(
    @PrimaryKey
    @ColumnInfo(name = COL_ID)
    val id: String,
    @ColumnInfo(name = COL_DATE)
    val date: Long?,
    @ColumnInfo(name = COL_HUMIDITY)
    val humidity: Int?,
    @ColumnInfo(name = COL_PRESSURE)
    val pressure: Int?
) {
    companion object {
        const val TABLE_NAME = "forecast"

        const val COL_ID = "id"
        const val COL_DATE = "date"
        const val COL_HUMIDITY = "humidity"
        const val COL_PRESSURE = "pressure"
    }
}