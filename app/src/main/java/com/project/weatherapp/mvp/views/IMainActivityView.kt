package com.project.weatherapp.mvp.views

import com.project.weatherapp.dataclasses.ForecastResult

/**
 * Created by anandmishra on 18/12/17.
 */
interface IMainActivityView {

    fun showLoading()

    fun hideLoading()

    fun handleData(weatherData: ForecastResult)
}