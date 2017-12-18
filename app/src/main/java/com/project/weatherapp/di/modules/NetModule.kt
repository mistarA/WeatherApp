package com.project.weatherapp.di.modules

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.project.weatherapp.network.WeatherApiInterface
import com.project.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by anandmishra on 18/12/17.
 */

@Module
class NetModule{



    @Provides
    @Singleton
    internal fun provideHttpCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }


    @Singleton
    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideOkHttp(cache: Cache): OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .cache(cache)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient, @Named("baseUrl") url: String): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(url)
                .build()
    }

    @Provides
    @Singleton
    fun provideGitApi(retrofit: Retrofit): WeatherApiInterface {
        return retrofit.create<WeatherApiInterface>(WeatherApiInterface::class.java)
    }
}