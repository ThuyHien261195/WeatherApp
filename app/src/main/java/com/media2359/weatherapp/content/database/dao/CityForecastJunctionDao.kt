package com.media2359.weatherapp.content.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.media2359.weatherapp.content.database.entities.CityForecastJunction
import com.media2359.weatherapp.content.database.entities.ForecastEntity
import com.media2359.weatherapp.content.database.entities.ForecastWeatherJunction

@Dao
interface CityForecastJunctionDao : BaseDao<CityForecastJunction> {
    @Query(
        """DELETE FROM ${CityForecastJunction.TABLE_NAME} WHERE ${CityForecastJunction.COL_CITY_ID} == :cityId"""
    )
    suspend fun deleteByCityId(cityId: Int)
}