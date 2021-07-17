package com.media2359.weatherapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.media2359.weatherapp.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}