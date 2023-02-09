package com.example.kotlinproject.utils

import android.app.Application
import com.example.kotlinproject.di.AppModule
import com.example.kotlinproject.di.component.AppComponent
import com.example.kotlinproject.di.component.DaggerAppComponent

class App: Application(){

    lateinit var appComponent: AppComponent

    fun provideAppComponent(): AppComponent{
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        return appComponent

    }

}