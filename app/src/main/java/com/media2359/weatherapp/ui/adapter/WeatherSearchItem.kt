package com.media2359.weatherapp.ui.adapter

import com.media2359.weatherapp.content.model.Forecast

/**
 * Base class for items used in Weather search screen
 */
sealed class WeatherSearchItem() {

    /**
     * Item's view type to be used in [WeatherSearchItem]. Using layout resource id is recommended.
     */
    abstract val type: Int

    fun bind(viewHolder: WeatherSearchVH) {
        displayIn(viewHolder)
    }

    /**
     * Uses `double dispatch` to avoid casting
     */
    abstract fun displayIn(viewHolder: WeatherSearchVH)

    /**
     * Called to check whether two objects represent the same item.
     * For example, if your items have unique ids, this method should check their id equality.
     */
    abstract fun sameAs(item: WeatherSearchItem): Boolean
}

/**
 * Forecast
 */
data class ForecastItem(
    val forecast: Forecast
): WeatherSearchItem() {
    override val type = ForecastItemVH.LAYOUT_ID

    override fun displayIn(viewHolder: WeatherSearchVH) {
        viewHolder.displayForecastViewHolder(this)
    }

    override fun sameAs(item: WeatherSearchItem): Boolean {
        return (item is ForecastItem) && item.forecast.id == forecast.id
    }
}


