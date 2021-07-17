package com.media2359.weatherapp.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.media2359.weatherapp.content.model.Forecast
import java.lang.IllegalArgumentException

/**
 * Adapter for searching weather forecast
 */
class WeatherSearchAdapter: ListAdapter<WeatherSearchItem, WeatherSearchVH>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<WeatherSearchItem>() {
            override fun areItemsTheSame(oldItem: WeatherSearchItem, newItem: WeatherSearchItem): Boolean {
                return oldItem.sameAs(newItem)
            }

            override fun areContentsTheSame(oldItem: WeatherSearchItem, newItem: WeatherSearchItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherSearchVH {
        val creator = WeatherSearchVH.CREATORS[viewType]
        return creator?.invoke(parent) ?: throw IllegalArgumentException("Invalid view type: $viewType")
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type

    override fun onBindViewHolder(holderItem: WeatherSearchVH, position: Int) {
        getItem(position)?.bind(holderItem)
    }
}