package com.project.weatherapp.network

import com.google.gson.Gson
import com.project.weatherapp.dataclasses.ForecastResult
import java.net.URL
/**
 * Created by anandmishra on 17/12/17.
 */
class ForecastRequest(private val zipCode: String) {

    companion object {
        private val APP_ID = "b6907d289e10d714a6e88b30761fae22"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = /*"$URL&APPID=$APP_ID&q="*/
        "http://samples.openweathermap.org/data/2.5/forecast/daily?zip=94040&appid=b6907d289e10d714a6e88b30761fae22"
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL/* + zipCode*/).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}