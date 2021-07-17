package com.media2359.weatherapp.content.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.media2359.weatherapp.content.database.entities.CityEntity
import com.media2359.weatherapp.content.database.entities.CityWithDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao : BaseDao<CityEntity> {

    @Transaction
    @Query(
        """SELECT * FROM ${CityEntity.TABLE_NAME}
        WHERE ${CityEntity.COL_ID} == :cityId"""
    )
    fun getForecastByCityId(cityId: Int): Flow<CityWithDetailsEntity>
}