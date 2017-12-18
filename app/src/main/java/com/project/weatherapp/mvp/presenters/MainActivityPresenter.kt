package com.project.weatherapp.mvp.presenters

import android.util.Log
import com.project.weatherapp.mvp.views.IMainActivityView
import com.project.weatherapp.network.WeatherApiInterface
import com.project.weatherapp.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by anandmishra on 18/12/17.
 */
class MainActivityPresenter @Inject constructor(weatherApiInterface: WeatherApiInterface): Presenter() {

    var mainActivityView: IMainActivityView? = null
    var mWeatherApiInterface = weatherApiInterface

    fun setView(iMainActivityView: IMainActivityView) {
        mainActivityView = iMainActivityView
    }
    init {
        this.mWeatherApiInterface = weatherApiInterface
    }

    fun getWeatherData() {
        compositeDisposable.add(mWeatherApiInterface.getWeatherData(Constants.BASE_URL,
                Constants.APP_CODE, Constants.ZIP_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Any>() {
                    override fun onSuccess(newsFeed: Any) {

                        mainActivityView?.hideLoading()
                        mainActivityView?.handleData(newsFeed)
                    }

                    override fun onError(e: Throwable) {

                        Log.d("Error", e.message)
                    }
                }))
    }
}