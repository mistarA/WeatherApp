package com.project.weatherapp.mvp.presenters

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by anandmishra on 18/12/17.
 */
open class Presenter{

    lateinit var compositeDisposable : CompositeDisposable

    fun start() {
        compositeDisposable = CompositeDisposable()
    }
    fun stop() {
        compositeDisposable.clear()
    }
}