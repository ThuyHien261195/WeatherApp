package com.media2359.weatherapp.content.database.entities

import androidx.room.*

@Entity(
    tableName = TemperatureEntity.TABLE_NAME,
    indices = [
        Index(TemperatureEntity.COL_ID)
    ],
    foreignKeys = [
        ForeignKey(
            entity = ForecastEntity::class,
            parentColumns = arrayOf(ForecastEntity.COL_ID),
            childColumns = arrayOf(TemperatureEntity.COL_FORECAST_ID),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TemperatureEntity(
    @PrimaryKey
    @ColumnInfo(name = COL_ID)
    val id: String,
    @ColumnInfo(name = COL_DAY)
    val day: Double?,
    @ColumnInfo(name = COL_NIGHT)
    val night: Double?,
    @ColumnInfo(name = COL_EVENING)
    val eve: Double?,
    @ColumnInfo(name = COL_MORNING)
    val morn: Double?,
    @ColumnInfo(name = COL_MIN)
    val min: Double?,
    @ColumnInfo(name = COL_MAX)
    val max: Double?,
    @ColumnInfo(name = COL_FORECAST_ID)
    val forecastId: String?
) {
    companion object {
        const val TABLE_NAME = "temperature"

        const val COL_ID = "id"
        const val COL_DAY = "day"
        const val COL_NIGHT = "night"
        const val COL_EVENING = "evening"
        const val COL_MORNING = "morning"
        const val COL_MIN = "min"
        const val COL_MAX = "max"
        const val COL_FORECAST_ID = "forecast_id"
    }
}