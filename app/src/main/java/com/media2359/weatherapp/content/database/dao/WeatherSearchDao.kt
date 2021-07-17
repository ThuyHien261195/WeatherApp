package com.media2359.weatherapp.content.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.media2359.weatherapp.content.database.entities.SearchEntity

@Dao
interface WeatherSearchDao : BaseDao<SearchEntity> {
    @Transaction
    @Query(
        """SELECT * FROM ${SearchEntity.TABLE_NAME}
        WHERE ${SearchEntity.COL_KEYWORD} LIKE :cityName"""
    )
    fun getWeatherSearch(cityName: String): SearchEntity?
}