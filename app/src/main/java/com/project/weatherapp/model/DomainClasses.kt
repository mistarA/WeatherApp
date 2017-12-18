package com.project.weatherapp.model

/**
 * Created by anandmishra on 17/12/17.
 */

data class ForecastList(val city: String, val country: String,
                        private val dailyForecast: List<Forecast>) {

    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int): Forecast = dailyForecast[position]
}

data class Forecast(val date: String, val description: String, val high: Int, val low: Int,
                    val iconUrl: String)