package com.project.weatherapp.model

/**
 * Created by anandmishra on 17/12/17.
 */

data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>)
data class Forecast(val date: String, val description: String, val high: Int, val low: Int)