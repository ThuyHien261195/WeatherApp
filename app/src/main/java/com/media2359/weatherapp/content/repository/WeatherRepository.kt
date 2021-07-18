package com.media2359.weatherapp.content.repository

import com.media2359.weatherapp.content.database.RoomTransactionExecutor
import com.media2359.weatherapp.content.database.dao.*
import com.media2359.weatherapp.content.database.entities.CityForecastJunction
import com.media2359.weatherapp.content.database.entities.ForecastWeatherJunction
import com.media2359.weatherapp.content.database.entities.SearchEntity
import com.media2359.weatherapp.content.mapper.*
import com.media2359.weatherapp.content.model.City
import com.media2359.weatherapp.content.model.wrapper.Resource
import com.media2359.weatherapp.content.network.WeatherService
import com.media2359.weatherapp.content.network.request.WeatherSearchRequest
import com.media2359.weatherapp.content.network.response.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val cityDao: CityDao,
    private val cityForecastJunctionDao: CityForecastJunctionDao,
    private val forecastDao: ForecastDao,
    private val forecastWeatherJunctionDao: ForecastWeatherJunctionDao,
    private val temperatureDao: TemperatureDao,
    private val weatherDao: WeatherDao,
    private val searchDao: WeatherSearchDao,
    private val transactionExecutor: RoomTransactionExecutor
) {
    suspend fun fetchWeatherSearch(searchRequest: WeatherSearchRequest): Flow<Resource<City>> =
        flow {
            try {
                val searchEntity = searchDao.getWeatherSearch(searchRequest.cityName)
                val cityId = if (searchEntity == null) {
                    // Get from server
                    emit(Resource.Loading())

                    val response = weatherService.getWeatherForecastList(
                        searchRequest.cityName,
                        searchRequest.numberDays,
                        searchRequest.appId,
                        searchRequest.units
                    )

                    storeSearch(searchRequest.cityName, response)
                    response.city.id
                } else {
                    if (searchEntity.cityId == null) {
                        emit(Resource.Error(Exception(searchEntity.message), null))
                    }
                    searchEntity.cityId
                }

                // Get from DB
                cityId?.let { id ->
                    emitAll(cityDao.getForecastByCityId(id).map {
                        Resource.Success(it.toCity())
                    })
                }
            } catch (e: Exception) {
                emit(Resource.Error(e, null))
                if (e is HttpException) {
                    searchDao.inserOrUpdate(
                        SearchEntity(searchRequest.cityName, null, e.message)
                    )
                }
            }
        }

    private suspend fun storeSearch(searchCityName: String, searchResponse: SearchResponse) {
        transactionExecutor.execute {
            searchDao.inserOrUpdate(
                SearchEntity(
                    searchCityName,
                    searchResponse.city.id,
                    searchResponse.message.toString()
                )
            )

            // insert or update city
            val cityEntity = searchResponse.toCityEntity()
            val forecastEntityList = searchResponse.forecastList?.map {
                it.toForecastEntity()
            }
            cityEntity.forecastIdList = forecastEntityList?.map { it.id }

            cityDao.inserOrUpdate(cityEntity)

            // delete city forecast junction
            cityForecastJunctionDao.deleteByCityId(cityEntity.id)

            // browse forecast list
            searchResponse.forecastList?.forEachIndexed { index, response ->
                val forecastEntity = forecastEntityList?.get(index) ?: return@forEachIndexed

                // insert or update forecast
                forecastDao.inserOrUpdate(forecastEntity)
                cityForecastJunctionDao.insert(
                    CityForecastJunction(
                        cityEntity.id,
                        forecastEntity.id
                    )
                )

                // insert or update temperature
                response.temp?.let { tempResponse ->
                    val tempEntity = tempResponse.toTemperatureEntity(forecastEntity.id)
                    temperatureDao.inserOrUpdate(tempEntity)
                }

                // delete forecast temp junction
                forecastWeatherJunctionDao.deleteByForecastId(forecastEntity.id)
                if (!response.weatherList.isNullOrEmpty()) {
                    val weatherEntity = response.weatherList[0].toWeatherEntity()
                    weatherDao.inserOrUpdate(weatherEntity)
                    forecastWeatherJunctionDao.inserOrUpdate(
                        ForecastWeatherJunction(
                            forecastEntity.id,
                            weatherEntity.id
                        )
                    )
                }
            }

            // delete unused forecast
            forecastDao.deleteUnused()

            // delete unused weather
            weatherDao.deleteUnused()
        }
    }
}