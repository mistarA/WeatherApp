package com.project.weatherapp.mvp.presenters

import android.util.Log
import android.util.LruCache
import com.project.weatherapp.dataclasses.ForecastResult
import com.project.weatherapp.mvp.views.IMainActivityView
import com.project.weatherapp.network.WeatherApiInterface
import com.project.weatherapp.utils.Constants
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by anandmishra on 18/12/17.
 */
class MainActivityPresenter @Inject constructor(mWeatherApiInterface: WeatherApiInterface) : Presenter() {

    lateinit var mainActivityView: IMainActivityView
    val cacheSize = 1024 * 1024 * 3
    private var cache: LruCache<String, ForecastResult>

    fun setView(iMainActivityView: IMainActivityView) {
        mainActivityView = iMainActivityView
    }

    init {
        cache = LruCache<String, ForecastResult>(cacheSize)
    }

    fun getWeatherData() {
        val weatherData: Any = getDataFromCache()
        if (weatherData is ForecastResult && weatherData.isUpdated) {
            mainActivityView.handleData(weatherData)
        } else {
            compositeDisposable.add(
                    networkData
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<ForecastResult>() {

                                override fun onError(e: Throwable) {
                                    Log.d("GetData", e.message)
                                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                }

                                override fun onSuccess(t: ForecastResult) {

                                    mainActivityView.handleData(t)
                                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                }

                            }))
        }

    }

    val dataCacheKey: String = "ApiData"
    val networkData: Single<ForecastResult> by lazy {
        mWeatherApiInterface
                .getWeatherData(Constants.BASE_URL, Constants.APP_CODE, Constants.ZIP_CODE)
                .doOnSuccess {
                    if (it is ForecastResult) {
                        Log.d("GetData", "From Network")
                        Log.d("GetData", "Saving To Cache")
                        it.isUpdated = true
                        cache.put(dataCacheKey, it)
                    }
                }
    }

    fun getDataFromCache(): Any {
        Log.d("GetData", "From Cache")
        if (cache[dataCacheKey] != null) {
            return cache[dataCacheKey]
        } else {
            return Any()
        }
    }
}