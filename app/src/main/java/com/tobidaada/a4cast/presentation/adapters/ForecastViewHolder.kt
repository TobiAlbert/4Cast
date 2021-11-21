package com.tobidaada.a4cast.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tobidaada.a4cast.databinding.ItemForecastListBinding
import com.tobidaada.a4cast.presentation.models.Forecast
import com.tobidaada.a4cast.utils.SHORT_DAY_PATTERN
import com.tobidaada.a4cast.utils.formatDateFromUnixTime

class ForecastViewHolder(private val binding: ItemForecastListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: Forecast) {
        binding.apply {
            Picasso.get()
                .load("https://openweathermap.org/img/wn/${forecast.icon}@2x.png")
                .into(forecastIcon)

            forecastDayTv.text = formatDateFromUnixTime(forecast.time, SHORT_DAY_PATTERN)
            forecastDescription.text = forecast.description
            forecastMinMaxTv.text =
                StringBuilder(forecast.maxTemperature.toString())
                    .append("℃/")
                    .append(forecast.minTemperature)
                    .append("℃")
                    .toString()
        }
    }
}