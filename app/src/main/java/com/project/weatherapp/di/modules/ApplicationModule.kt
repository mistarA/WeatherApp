package com.project.weatherapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.jetbrains.anko.ctx

/**
 * Created by anandmishra on 18/12/17.
 */

@Module
class ApplicationModule (val application : Application){

    @Provides
    fun provideApp() :Context {
        return application
    }

}