package com.media2359.weatherapp.content.mapper

import com.media2359.weatherapp.content.database.entities.CityWithDetailsEntity
import com.media2359.weatherapp.content.database.entities.ForecastWithDetailsEntity
import com.media2359.weatherapp.content.model.City
import com.media2359.weatherapp.content.model.Forecast
import com.media2359.weatherapp.utils.convertLongToDateTime

fun CityWithDetailsEntity.toCity() = City(
    id = cityEntity.id,
    name = cityEntity.name,
    forecastList = forecastEntities
        ?.sortedBy { data -> cityEntity.forecastIdList?.indexOf(data.forecatEntity.id) }
        ?.map { it.toForecast() }
)

fun ForecastWithDetailsEntity.toForecast(): Forecast {
    val temp = (temperatureEntity?.min ?: 0.0) + (temperatureEntity?.max ?: 0.0)
    return Forecast (
        id = forecatEntity.id,
        date = forecatEntity.date?.convertLongToDateTime(),
        aveTemp = (temp) / 2.0,
        pressure = forecatEntity.pressure,
        humidity = forecatEntity.humidity,
        description = weatherEntity?.description
    )
}