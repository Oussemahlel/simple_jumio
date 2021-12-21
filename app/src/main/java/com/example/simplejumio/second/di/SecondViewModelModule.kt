package com.example.simplejumio.second.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplejumio.common.ui.CommonViewModelKey
import com.example.simplejumio.second.ui.SecondViewModel
import com.example.simplejumio.second.ui.fragments.JumioViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SecondViewModelModule {

    @Binds
    abstract fun bindViewModelProviderFactory(providerFactory: SecondViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @CommonViewModelKey(SecondViewModel::class)
    abstract fun bindSecondViewModel(secondViewModel: SecondViewModel): ViewModel

    @Binds
    @IntoMap
    @CommonViewModelKey(JumioViewModel::class)
    abstract fun bindJumioViewModel(jumioViewModel: JumioViewModel): ViewModel

}