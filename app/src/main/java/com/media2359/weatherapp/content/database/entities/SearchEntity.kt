package com.media2359.weatherapp.content.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.media2359.weatherapp.content.database.entities.CityForecastJunction.Companion.COL_CITY_ID

@Entity(
    tableName = SearchEntity.TABLE_NAME,
    indices = [
        Index(SearchEntity.COL_KEYWORD)
    ]
)
data class SearchEntity(
    @PrimaryKey
    @ColumnInfo(name = COL_KEYWORD)
    val keyword: String,
    @ColumnInfo(name = COL_CITY_ID)
    val cityId: Int?,
    @ColumnInfo(name = COL_MESSAGE)
    val message: String?,
) {
    companion object {
        const val TABLE_NAME = "search_city"

        const val COL_KEYWORD = "keyword"
        const val COL_CITY_ID = "city_id"
        const val COL_MESSAGE = "message"
    }

}