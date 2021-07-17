package com.media2359.weatherapp.content.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.media2359.weatherapp.content.database.entities.CityForecastJunction
import com.media2359.weatherapp.content.database.entities.ForecastEntity

@Dao
interface ForecastDao: BaseDao<ForecastEntity> {
    @Query("""DELETE FROM ${ForecastEntity.TABLE_NAME} WHERE ${ForecastEntity.COL_ID} 
        NOT IN (SELECT DISTINCT ${CityForecastJunction.COL_FORECAST_ID} FROM ${CityForecastJunction.TABLE_NAME})""")
    suspend fun deleteUnused()
}