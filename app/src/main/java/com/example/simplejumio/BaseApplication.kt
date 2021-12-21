package com.example.simplejumio

import android.app.Application
import com.example.simplejumio.common.di.AppComponent
import com.example.simplejumio.common.di.DaggerAppComponent
import com.example.simplejumio.main.di.MainComponent
import com.example.simplejumio.second.di.SecondComponent


class BaseApplication : Application() {
    lateinit var appComponent: AppComponent
    private var mainComponent: MainComponent? = null
    private var secondComponent: SecondComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    fun mainComponent(): MainComponent {
        if (mainComponent == null)
            mainComponent = appComponent.mainComponent().create()
        return mainComponent as MainComponent
    }

    fun secondComponent(): SecondComponent {
        if (secondComponent == null)
            secondComponent = appComponent.secondComponent().create()
        return secondComponent as SecondComponent
    }

}