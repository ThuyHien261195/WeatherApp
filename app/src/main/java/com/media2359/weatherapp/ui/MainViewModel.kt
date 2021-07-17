package com.media2359.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.media2359.weatherapp.content.database.AppDatabase
import com.media2359.weatherapp.content.network.request.WeatherSearchRequest
import com.media2359.weatherapp.content.repository.WeatherRepository
import com.media2359.weatherapp.manager.AppManager
import com.media2359.weatherapp.utils.KEEP_CACHE_PERIOD
import com.media2359.weatherapp.utils.SEARCH_KEYWORD_SIZE
import com.media2359.weatherapp.utils.tickerFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val appDatabase: AppDatabase,
    private val appManager: AppManager,
    dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val autoClearCacheFlow = tickerFlow(
        KEEP_CACHE_PERIOD,
        (appManager.lastClearCacheTimestamp + KEEP_CACHE_PERIOD) - System.currentTimeMillis()
    ).onEach {
        clearCache()
    }

    init {
        autoClearCacheFlow.launchIn(viewModelScope)
    }

    private val keywordMSFlow = MutableStateFlow("")

    private val searchResult = keywordMSFlow
        .filter { it.length >= SEARCH_KEYWORD_SIZE }
        .flatMapLatest {
            val request = WeatherSearchRequest(it)
            weatherRepository.fetchWeatherSearch(request)
        }.flowOn(dispatcherIO)

    val searchResultLiveData = searchResult.asLiveData()

    fun searchKeyword(keyword: String) {
        keywordMSFlow.value = keyword
    }

    private fun clearCache() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                appDatabase.clearAllTables()
                appManager.lastClearCacheTimestamp = System.currentTimeMillis()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}