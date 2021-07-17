package com.media2359.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.media2359.weatherapp.R
import com.media2359.weatherapp.content.model.Forecast
import com.media2359.weatherapp.databinding.ItemForecastBinding
import com.media2359.weatherapp.ui.BaseViewHolder

abstract class WeatherSearchVH(view: View) : BaseViewHolder(view) {

    companion object {
        /**
         * Map a `viewType` to a function that can create [WeatherSearchVH]
         */
        val CREATORS: Map<Int, (ViewGroup) -> WeatherSearchVH> = mapOf(
            ForecastItemVH.LAYOUT_ID to ForecastItemVH::create
        )
    }

    open fun displayForecastViewHolder(item: ForecastItem) {}
}

/**
 * ViewHolder corresponds to [ForecastItem]
 */
class ForecastItemVH(view: View) : WeatherSearchVH(view) {

    companion object {
        const val LAYOUT_ID = R.layout.item_forecast

        fun create(parent: ViewGroup): WeatherSearchVH {
            val view = LayoutInflater.from(parent.context).inflate(LAYOUT_ID, parent, false)
            return ForecastItemVH(view)
        }
    }

    private val binding = ItemForecastBinding.bind(view)

    override fun displayForecastViewHolder(item: ForecastItem) {
        val forecast = item.forecast
        binding.run {
            tvDate.text = resource.getString(R.string.label_date, forecast.date)
            tvAverageTemp.text =
                resource.getString(R.string.label_average_temperature, forecast.aveTemp)
            tvPressure.text = resource.getString(R.string.label_pressure, forecast.pressure)
            tvHumidity.text = resource.getString(R.string.label_humidity, forecast.humidity)
            tvDescription.text =
                resource.getString(R.string.label_description, forecast.description)
        }
    }
}