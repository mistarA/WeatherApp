package com.project.weatherapp.command

/**
 * Created by anandmishra on 17/12/17.
 */
interface Command<out T> {
    fun execute(): T
}