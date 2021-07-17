package com.media2359.weatherapp.content.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AppDBConverter {
    @JvmStatic
    @TypeConverter
    fun stringListToString(strings: List<String>?): String? = strings?.joinToString(separator = ",")

    @JvmStatic
    @TypeConverter
    fun stringToStringList(string: String?): List<String>? = string?.split(",")
}