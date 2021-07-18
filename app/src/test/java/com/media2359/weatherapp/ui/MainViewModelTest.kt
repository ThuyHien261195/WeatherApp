package com.media2359.weatherapp.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.media2359.weatherapp.content.database.AppDatabase
import com.media2359.weatherapp.content.database.dao.*
import com.media2359.weatherapp.content.database.entities.SearchEntity
import com.media2359.weatherapp.content.model.wrapper.Resource
import com.media2359.weatherapp.content.network.WeatherService
import com.media2359.weatherapp.content.repository.WeatherRepository
import com.media2359.weatherapp.di.module.NetworkModule
import com.media2359.weatherapp.manager.AppManager
import com.media2359.weatherapp.ui.test_util.*
import com.media2359.weatherapp.utils.KEEP_CACHE_PERIOD
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import okhttp3.OkHttpClient
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    private lateinit var cityDao: CityDao
    private lateinit var cityForecastJunctionDao: CityForecastJunctionDao
    private lateinit var forecastDao: ForecastDao
    private lateinit var forecastWeatherJunctionDao: ForecastWeatherJunctionDao
    private lateinit var temperatureDao: TemperatureDao
    private lateinit var weatherDao: WeatherDao
    private lateinit var searchDao: WeatherSearchDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var mockWebServer: MockWebServer
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var mainViewModel: MainViewModel
    private lateinit var appManger: AppManager

    private val gson = NetworkModule.provideGson()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()  // Run sycnchonous

    @get:Rule
    var mainCoroutineScopeRule = MainCoroutineScopeRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .setTransactionExecutor(mainCoroutineScopeRule.dispatcher.asExecutor())
            .setQueryExecutor(mainCoroutineScopeRule.dispatcher.asExecutor())
            .build()

        cityDao = appDatabase.cityDao()
        cityForecastJunctionDao = appDatabase.cityForecastJunctionDao()
        forecastDao = appDatabase.forecastDao()
        forecastWeatherJunctionDao = appDatabase.forecastWeatherJunctionDao()
        temperatureDao = appDatabase.temperatureDao()
        weatherDao = appDatabase.weatherDao()
        searchDao = appDatabase.weatherSearchDao()

        appManger = AppManager(
            context.getSharedPreferences("TEST_DEP_PREF_APP", Context.MODE_PRIVATE)
        )

        mockWebServer = MockWebServer()

        val okHttpClient = OkHttpClient.Builder()
            .dispatcher(okhttp3.Dispatcher(OkHttpTestExecutor(mainCoroutineScopeRule.dispatcher)))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val weatherService = retrofit.create(WeatherService::class.java)

        weatherRepository = WeatherRepository(
            weatherService,
            cityDao,
            cityForecastJunctionDao,
            forecastDao,
            forecastWeatherJunctionDao,
            temperatureDao,
            weatherDao,
            searchDao,
            TestTransactionExecutor(appDatabase)
        )
        mainViewModel = MainViewModel(
            weatherRepository,
            appDatabase,
            appManger,
            mainCoroutineScopeRule.dispatcher
        )
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
        mockWebServer.shutdown()
    }

    @Test
    fun searchWeather_keywordSizeLargerThan3_hasData() = mainCoroutineScopeRule.runBlockingTest {
        mockWebServer.enqueue(MockResponse().setBodyFromFile("weather_saigon.json"))

        mainViewModel.runTest {
            mainViewModel.searchKeyword("Saigon")

            mainViewModel.searchResultLiveData.observeForTesting {
                assertThat(
                    mainViewModel.searchResultLiveData.value?.getAvailableData()?.forecastList?.size,
                    equalTo(3)
                )
            }
        }
    }

    @Test
    fun searchWeather_keywordSizeLargerThan3_error() = mainCoroutineScopeRule.runBlockingTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(401))

        mainViewModel.runTest {
            mainViewModel.searchKeyword("Saigon")

            mainViewModel.searchResultLiveData.observeForTesting {
                assert(mainViewModel.searchResultLiveData.value is Resource.Error)
            }
        }
    }

    @Test
    fun searchWeather_keywordSizeLessThan_noCallApo() = mainCoroutineScopeRule.runBlockingTest {
        mockWebServer.enqueue(MockResponse().setBodyFromFile("weather_saigon.json"))

        mainViewModel.runTest {
            mainViewModel.searchKeyword("S")

            assertThat(mockWebServer.requestCount, equalTo(0))
        }
    }

    @Test
    fun clearCache_success() = mainCoroutineScopeRule.runBlockingTest {
        mainViewModel.runTest {
            val searchEntity = SearchEntity("Saigon", null, "error")
            runBlocking {
                searchDao.insert(searchEntity)
            }

            val searchFromDb = searchDao.getWeatherSearch("Saigon")
            assertThat(searchFromDb, equalTo(searchEntity))

            runBlocking {
                delay(KEEP_CACHE_PERIOD + 10000L)
            }

            val cacheSearch = searchDao.getWeatherSearch("Saigon")
            assertThat(cacheSearch, equalTo(null))
        }
    }
}