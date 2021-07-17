package com.media2359.weatherapp.content.database

import android.content.Context
import androidx.room.*
import com.media2359.weatherapp.content.database.dao.*
import com.media2359.weatherapp.content.database.entities.*

@Database(
    entities = [
        CityEntity::class,
        CityForecastJunction::class,
        ForecastEntity::class,
        ForecastWeatherJunction::class,
        TemperatureEntity::class,
        WeatherEntity::class,
        SearchEntity::class
    ],
    version = AppDatabase.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(AppDBConverter::class)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_VERSION = 1

        private const val DATABASE_NAME = "WEATHER_DB"

        fun build(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun cityDao(): CityDao

    abstract fun cityForecastJunctionDao(): CityForecastJunctionDao

    abstract fun forecastDao(): ForecastDao

    abstract fun forecastWeatherJunctionDao(): ForecastWeatherJunctionDao

    abstract fun temperatureDao(): TemperatureDao

    abstract fun weatherDao(): WeatherDao

    abstract fun weatherSearchDao(): WeatherSearchDao
}