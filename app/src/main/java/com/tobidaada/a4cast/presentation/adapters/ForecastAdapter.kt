package com.tobidaada.a4cast.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tobidaada.a4cast.R
import com.tobidaada.a4cast.presentation.models.Forecast
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ForecastAdapter @Inject constructor(
    @ActivityContext private val context: Context
): RecyclerView.Adapter<ForecastViewHolder>() {

    private val forecasts = mutableListOf<Forecast>()

    fun addItems(forecastList: List<Forecast>) {
        forecasts.clear().also { forecasts.addAll(forecastList) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val rootView =
            LayoutInflater.from(context).inflate(R.layout.item_forecast_list, parent, false)
        return ForecastViewHolder(rootView)
    }

    override fun getItemCount(): Int = forecasts.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bind(forecasts[position])
}