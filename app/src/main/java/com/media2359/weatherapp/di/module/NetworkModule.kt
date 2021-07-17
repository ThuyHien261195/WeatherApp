package com.media2359.weatherapp.di.module

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.media2359.weatherapp.content.network.WeatherService
import com.media2359.weatherapp.utils.LOG_TAG
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideDispatchersIO() = Dispatchers.IO

    @Provides
    @Singleton
    @JvmStatic
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor { message ->
            Timber.d("${LOG_TAG}_$message")
        }
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val reqBuilder = chain.request().newBuilder()
                .addHeader("Accept", "*/*")
                .addHeader("Content-Type", "application/json")
            chain.proceed(reqBuilder.build())
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().addConverterFactory((GsonConverterFactory.create(gson)))
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideCommonRetrofit(
        httpClientBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder,
        interceptor: Interceptor
    ): Retrofit {
        httpClientBuilder.addInterceptor(interceptor)
        return retrofitBuilder
            .client(httpClientBuilder.build())
            .baseUrl(com.media2359.weatherapp.BuildConfig.END_POINT)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}