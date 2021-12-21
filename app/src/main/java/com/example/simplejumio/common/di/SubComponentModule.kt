package com.example.simplejumio.common.di

import com.example.simplejumio.main.di.MainComponent
import com.example.simplejumio.second.di.SecondComponent
import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class,
        SecondComponent::class
    ]
)
class SubComponentModule