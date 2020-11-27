package com.tobidaada.a4cast.presentation.adapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tobidaada.a4cast.R
import com.tobidaada.a4cast.presentation.models.Forecast
import com.tobidaada.a4cast.utils.SHORT_DAY_PATTERN
import com.tobidaada.a4cast.utils.formatDateFromUnixTime
import java.lang.StringBuilder

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(forecast: Forecast) {
        itemView.apply {
            Picasso.get()
                .load("https://openweathermap.org/img/wn/${forecast.icon}@2x.png")
                .into(findViewById<ImageView>(R.id.forecastIcon))

            findViewById<TextView>(R.id.forecastDayTv).text = formatDateFromUnixTime(forecast.time, SHORT_DAY_PATTERN)
            findViewById<TextView>(R.id.forecastDescription).text = forecast.description
            findViewById<TextView>(R.id.forecastMinMaxTv).text =
                StringBuilder(forecast.maxTemperature.toString())
                    .append("℃/")
                    .append(forecast.minTemperature)
                    .append("℃")
                    .toString()
        }
    }
}