package com.tobidaada.remote.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("timezone_offset")
    val timezoneOffset: Int,

    @SerializedName("current")
    val current: CurrentWeatherRemote,

    @SerializedName("daily")
    val daily: List<WeeklyForecast>
)