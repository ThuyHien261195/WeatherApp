package com.media2359.weatherapp.content.mapper

import com.media2359.weatherapp.content.database.entities.CityEntity
import com.media2359.weatherapp.content.database.entities.ForecastEntity
import com.media2359.weatherapp.content.database.entities.TemperatureEntity
import com.media2359.weatherapp.content.database.entities.WeatherEntity
import com.media2359.weatherapp.content.network.response.ForecastResponse
import com.media2359.weatherapp.content.network.response.SearchResponse

fun SearchResponse.toCityEntity() = CityEntity(
    id = city.id,
    name = city.name,
    lat = city.coordinate?.lat,
    lon = city.coordinate?.lon,
    country = city.country,
    population = city.population,
    timezone = city.timezone,
    forecastIdList = emptyList()
)

fun ForecastResponse.toForecastEntity() = ForecastEntity(
    id = this.hashCode().toString(),
    date = date,
    humidity = humidity,
    pressure = pressure
)

fun ForecastResponse.TemperatureResponse.toTemperatureEntity(forecastId: String) = TemperatureEntity(
    id = this.hashCode().toString(),
    day = day,
    min = min,
    max = max,
    night = night,
    eve = eve,
    morn = morn,
    forecastId = forecastId
)

fun ForecastResponse.WeatherResponse.toWeatherEntity() = WeatherEntity(
    id = id,
    main = main,
    description = description,
    icon = icon
)