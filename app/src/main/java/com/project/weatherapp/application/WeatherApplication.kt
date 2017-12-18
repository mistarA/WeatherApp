package com.project.weatherapp.application

import android.app.Application
import com.project.weatherapp.di.components.DaggerWeatherAppComponent
import com.project.weatherapp.di.components.WeatherAppComponent
import com.project.weatherapp.di.modules.ApplicationModule

/**
 * Created by anandmishra on 18/12/17.
 */

class WeatherApplication : Application() {

    val component : WeatherAppComponent by lazy {
        DaggerWeatherAppComponent.
                builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}