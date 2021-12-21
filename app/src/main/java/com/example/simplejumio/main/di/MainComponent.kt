package com.example.simplejumio.main.di

import com.example.simplejumio.main.ui.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(
    modules = [
        MainViewModelModule::class
    ]
)
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainActivity: MainActivity)
}