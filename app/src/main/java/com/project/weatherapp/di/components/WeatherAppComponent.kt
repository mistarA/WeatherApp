package com.project.weatherapp.di.components

import com.project.weatherapp.application.WeatherApplication
import com.project.weatherapp.di.modules.ApplicationModule
import com.project.weatherapp.di.modules.NetModule
import com.project.weatherapp.views.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by anandmishra on 18/12/17.
 */

@Singleton
@Component (modules = arrayOf(ApplicationModule::class, NetModule::class))
interface WeatherAppComponent {

    fun inject(mainActivity : MainActivity)
    fun inject(mainActivity: WeatherApplication)
}