package com.project.weatherapp.views

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.project.weatherapp.R
import com.project.weatherapp.adapters.ForecastListAdapter
import com.project.weatherapp.application.WeatherApplication
import com.project.weatherapp.dataclasses.ForecastResult
import com.project.weatherapp.mvp.presenters.MainActivityPresenter
import com.project.weatherapp.mvp.views.IMainActivityView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainActivityView {

    @Inject lateinit var mainActivityPresenter : MainActivityPresenter

    lateinit var foreCastList: RecyclerView
    val component by lazy {
        app.component
    }

    val Activity.app: WeatherApplication
        get() = application as WeatherApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        foreCastList = findViewById<RecyclerView>(R.id.fore_cast_rv)
        foreCastList.layoutManager = LinearLayoutManager(this)
        init()
    }

    private fun init() {
        component.inject(this)
        mainActivityPresenter.setView(this)
    }

    override fun onStart() {
        super.onStart()
        mainActivityPresenter.start()
        mainActivityPresenter.getWeatherData()
    }

    override fun onStop() {
        super.onStop()
        mainActivityPresenter.stop()
    }

    override fun showLoading() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var data : ForecastResult

    override fun handleData(weatherData: ForecastResult) {
        data = weatherData
        foreCastList.adapter = ForecastListAdapter(data.list)
        Log.d("Data", weatherData.toString())
    }
}
