package com.media2359.weatherapp.content.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CityWithDetailsEntity(
    @Embedded
    val cityEntity: CityEntity,

    @Relation(
        parentColumn = CityEntity.COL_ID,
        entityColumn = ForecastEntity.COL_ID,
        entity = ForecastEntity::class,
        associateBy = Junction(
            value = CityForecastJunction::class,
            parentColumn = CityForecastJunction.COL_CITY_ID,
            entityColumn = CityForecastJunction.COL_FORECAST_ID
        )
    )
    val forecastEntities: List<ForecastWithDetailsEntity>? = emptyList()
)