package com.example.simplejumio.second.di

import com.example.simplejumio.second.ui.SecondActivity
import com.example.simplejumio.second.ui.fragments.JumioFragment
import dagger.Subcomponent

@SecondScope
@Subcomponent(
    modules = [
        SecondViewModelModule::class
    ]
)
interface SecondComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SecondComponent
    }

    fun inject(secondActivity: SecondActivity)
    fun inject(jumioFragment: JumioFragment)
}