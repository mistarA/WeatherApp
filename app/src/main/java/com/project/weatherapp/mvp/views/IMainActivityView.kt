package com.project.weatherapp.mvp.views

/**
 * Created by anandmishra on 18/12/17.
 */
interface IMainActivityView {

    fun showLoading()

    fun hideLoading()

    fun handleData(newsFeed: Any)
}