package com.media2359.weatherapp.content.database.dao

import androidx.room.Dao
import com.media2359.weatherapp.content.database.entities.TemperatureEntity

@Dao
interface TemperatureDao : BaseDao<TemperatureEntity> {
}