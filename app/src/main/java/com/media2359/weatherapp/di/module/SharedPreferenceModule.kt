package com.media2359.weatherapp.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SharedPreferenceModule {

    private const val DEP_PREF_APP = "DEP_PREF_APP"

    @Provides
    @Singleton
    fun provideAppManagerPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(DEP_PREF_APP, Context.MODE_PRIVATE)
    }
}