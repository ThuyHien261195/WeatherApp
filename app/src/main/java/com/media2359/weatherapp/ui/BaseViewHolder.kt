package com.media2359.weatherapp.ui

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract  class BaseViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
    val context: Context
        get() = itemView.context

    val resource: Resources
        get() = context.resources
}