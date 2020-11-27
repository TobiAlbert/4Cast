package com.tobidaada.remote.models

import com.google.gson.annotations.SerializedName

data class WeeklyForecast(
    @SerializedName("dt")
    val dateTime: Long,

    @SerializedName("weather")
    val weather: List<Weather>,

    @SerializedName("temp")
    val temp: Temp
)

data class Temp(
    @SerializedName("min")
    val minDailyTemp: Float,

    @SerializedName("max")
    val maxDailyTemp: Float
)


