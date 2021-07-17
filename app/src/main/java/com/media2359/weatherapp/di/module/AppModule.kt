package com.media2359.weatherapp.di.module

import android.app.Application
import android.content.Context
import com.media2359.weatherapp.di.scope.ActivityScope
import com.media2359.weatherapp.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindContext(application: Application): Context

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [ActivityViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}