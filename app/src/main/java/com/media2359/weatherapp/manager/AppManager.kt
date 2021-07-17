package com.media2359.weatherapp.manager

import android.content.SharedPreferences
import com.media2359.weatherapp.manager.preferences.LongPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppManager @Inject constructor(
    appPrefs: SharedPreferences,
) {
    companion object {
        const val PREF_LAST_CLEAR_CACHE_TIMESTAMP = "PREF_LAST_CLEAR_CACHE_TIMESTAMP"
    }

    var lastClearCacheTimestamp by LongPreference(
        appPrefs,
        PREF_LAST_CLEAR_CACHE_TIMESTAMP,
        0L
    )
}