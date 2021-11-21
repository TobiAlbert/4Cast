package com.tobidaada.a4cast.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tobidaada.a4cast.databinding.ItemForecastListBinding
import com.tobidaada.a4cast.presentation.models.Forecast
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ForecastAdapter @Inject constructor(
    @ActivityContext private val context: Context
) : ListAdapter<Forecast, ForecastViewHolder>(DiffCallback) {

    private val forecasts = mutableListOf<Forecast>()

    fun addItems(forecastList: List<Forecast>) {
        val currentSize = itemCount
        val positionStart = 0

        forecasts.clear()
        notifyItemRangeRemoved(positionStart, currentSize)
        forecasts.addAll(forecastList)
        notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemForecastListBinding.inflate(inflater, parent, false)
        return ForecastViewHolder(binding)
    }

    override fun getItemCount(): Int = forecasts.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bind(forecasts[position])

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Forecast>() {
            override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean =
                oldItem.time == newItem.time &&
                oldItem.minTemperature == newItem.minTemperature &&
                oldItem.maxTemperature == newItem.maxTemperature

            override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean =
                oldItem == newItem
        }
    }
}