package com.project.weatherapp.network

import com.project.weatherapp.dataclasses.ForecastResult
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by anandmishra on 18/12/17.
 */
interface WeatherApiInterface {

    @GET()
    fun getWeatherData(@Url url : String, @Query("appid") appCode : String, @Query("zip") zipCode : String) : Single<ForecastResult>

}