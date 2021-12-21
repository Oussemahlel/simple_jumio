package com.example.simplejumio.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplejumio.common.ui.CommonViewModelKey
import com.example.simplejumio.main.di.factories.MainViewModelProviderFactory
import com.example.simplejumio.main.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    abstract fun bindViewModelProviderFactory(providerFactory: MainViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @CommonViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

}