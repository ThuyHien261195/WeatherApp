package com.media2359.weatherapp.content.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.media2359.weatherapp.content.database.entities.CityForecastJunction
import com.media2359.weatherapp.content.database.entities.ForecastEntity
import com.media2359.weatherapp.content.database.entities.ForecastWeatherJunction

@Dao
interface ForecastWeatherJunctionDao: BaseDao<ForecastWeatherJunction> {
    @Query(
        """DELETE FROM ${ForecastWeatherJunction.TABLE_NAME} WHERE ${ForecastWeatherJunction.COL_FORECAST_ID} LIKE :forecastId"""
    )
    suspend fun deleteByForecastId(forecastId: String)
}