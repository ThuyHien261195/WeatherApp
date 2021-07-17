package com.media2359.weatherapp.content.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = WeatherEntity.TABLE_NAME,
    indices = [
        Index(WeatherEntity.COL_ID)
    ]
)
data class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = COL_ID)
    val id: Int,
    @ColumnInfo(name = COL_MAIN)
    val main: String?,
    @ColumnInfo(name = COL_DESCRIPTION)
    val description: String?,
    @ColumnInfo(name = COL_ICON)
    val icon: String?,
) {
    companion object {
        const val TABLE_NAME = "weather"

        const val COL_ID = "id"
        const val COL_MAIN = "main"
        const val COL_DESCRIPTION = "description"
        const val COL_ICON = "icon"
    }

}