package com.media2359.weatherapp.content.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.media2359.weatherapp.content.database.entities.ForecastWeatherJunction
import com.media2359.weatherapp.content.database.entities.WeatherEntity

@Dao
interface WeatherDao : BaseDao<WeatherEntity> {

    @Query(
        """DELETE FROM ${WeatherEntity.TABLE_NAME} WHERE ${WeatherEntity.COL_ID} 
        NOT IN (SELECT DISTINCT ${ForecastWeatherJunction.COL_WEATHER_ID} FROM ${ForecastWeatherJunction.TABLE_NAME})"""
    )
    suspend fun deleteUnused()
}