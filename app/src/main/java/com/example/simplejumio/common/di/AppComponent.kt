package com.example.simplejumio.common.di

import android.app.Application
import com.example.simplejumio.BaseActivity
import com.example.simplejumio.main.di.MainComponent
import com.example.simplejumio.second.di.SecondComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        SubComponentModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(baseActivity: BaseActivity)
    fun mainComponent(): MainComponent.Factory
    fun secondComponent(): SecondComponent.Factory
}