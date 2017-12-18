package com.project.weatherapp.views

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.project.weatherapp.R
import com.project.weatherapp.application.WeatherApplication
import com.project.weatherapp.mvp.presenters.MainActivityPresenter
import com.project.weatherapp.mvp.views.IMainActivityView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainActivityView {

    @Inject lateinit var mainActivityPresenter : MainActivityPresenter
    val component by lazy {
        app.component
    }

    val Activity.app: WeatherApplication
        get() = application as WeatherApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val foreCastList = findViewById<RecyclerView>(R.id.fore_cast_rv)
        foreCastList.layoutManager = LinearLayoutManager(this)
        component.inject(this)
        mainActivityPresenter.setView(this)
        /*doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                foreCastList.adapter = ForecastListAdapter(result)
            }
        }*/
    }

    override fun onStart() {
        super.onStart()
        mainActivityPresenter.start()
        if (data == null){
            mainActivityPresenter.getWeatherData()
        }
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

    private var data: Any? = null

    override fun handleData(newsFeed: Any) {
        data = newsFeed
        Log.d("Data", newsFeed.toString())
    }
}
