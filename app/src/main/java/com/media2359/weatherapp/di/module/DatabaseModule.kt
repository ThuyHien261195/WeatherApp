package com.media2359.weatherapp.di.module

import android.content.Context
import com.media2359.weatherapp.content.database.AppDatabase
import com.media2359.weatherapp.content.database.DefaultRoomTransactionExecutor
import com.media2359.weatherapp.content.database.RoomTransactionExecutor
import com.media2359.weatherapp.content.database.dao.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = AppDatabase.build(context)

    @Singleton
    @Provides
    fun provideTransactionExecutor(db: AppDatabase): RoomTransactionExecutor =
        DefaultRoomTransactionExecutor(db)

    @Singleton
    @Provides
    fun provideCityDao(db: AppDatabase): CityDao = db.cityDao()

    @Singleton
    @Provides
    fun provideCityForecastJunctionDao(db: AppDatabase): CityForecastJunctionDao =
        db.cityForecastJunctionDao()

    @Singleton
    @Provides
    fun provideForecastDao(db: AppDatabase): ForecastDao = db.forecastDao()

    @Singleton
    @Provides
    fun provideForecastWeatherJunctionDao(db: AppDatabase): ForecastWeatherJunctionDao =
        db.forecastWeatherJunctionDao()

    @Singleton
    @Provides
    fun provideTemperatureDao(db: AppDatabase): TemperatureDao = db.temperatureDao()

    @Singleton
    @Provides
    fun provideWeatherDao(db: AppDatabase): WeatherDao = db.weatherDao()

    @Singleton
    @Provides
    fun provideWeatherSearchDao(db: AppDatabase): WeatherSearchDao = db.weatherSearchDao()
}