package com.project.weatherapp.command

import com.project.weatherapp.mappers.ForecastDataMapper
import com.project.weatherapp.model.ForecastList
import com.project.weatherapp.network.ForecastRequest

/**
 * Created by anandmishra on 17/12/17.
 */


class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}