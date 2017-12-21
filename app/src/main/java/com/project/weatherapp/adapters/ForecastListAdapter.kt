package com.project.weatherapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.project.weatherapp.R
import com.project.weatherapp.dataclasses.Forecast
import com.project.weatherapp.utils.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * Created by anandmishra on 16/12/17.
 */

class ForecastListAdapter(private val weekForecast: List<Forecast>) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature)

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                //Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = dt.toString()
                descriptionView.text = weather.get(0).description
                maxTemperatureView.text = "${temp.max}º"
                minTemperatureView.text = "${temp.min}º"
                itemView.setOnClickListener  { this@ViewHolder.itemView.ctx.toast("Hey ".plus(descriptionView.text))}
            }
        }
    }
}
