package com.media2359.weatherapp.di

import android.app.Application
import com.media2359.weatherapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
    SharedPreferenceModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Builder {
        fun buildAppComponent(@BindsInstance application: Application): AppComponent
    }
}