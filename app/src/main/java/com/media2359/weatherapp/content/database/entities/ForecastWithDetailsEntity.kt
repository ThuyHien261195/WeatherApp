package com.media2359.weatherapp.content.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class ForecastWithDetailsEntity(
    @Embedded
    val forecatEntity: ForecastEntity,

    @Relation(
        parentColumn = ForecastEntity.COL_ID,
        entityColumn = TemperatureEntity.COL_FORECAST_ID
    )
    val temperatureEntity: TemperatureEntity? = null,

    @Relation(
        parentColumn = ForecastEntity.COL_ID,
        entityColumn = WeatherEntity.COL_ID,
        associateBy = Junction(
            value = ForecastWeatherJunction::class,
            parentColumn = ForecastWeatherJunction.COL_FORECAST_ID,
            entityColumn = ForecastWeatherJunction.COL_WEATHER_ID
        )
    )
    val weatherEntity: WeatherEntity? = null
)