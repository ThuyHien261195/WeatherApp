package com.media2359.weatherapp.di.module

import androidx.lifecycle.ViewModel
import com.media2359.weatherapp.di.ViewModelKey
import com.media2359.weatherapp.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}