package com.tobidaada.remote.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherRemote(
    @SerializedName("dt")
    val dateTime: Long,

    @SerializedName("temp")
    val temperature: Float,

    @SerializedName("feels_like")
    val feelsLikeTemperature: Float,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("uvi")
    val ultraVioletIndex: Float,

    @SerializedName("wind_speed")
    val windSpeed: Float,

    @SerializedName("weather")
    val weather: List<Weather>
)


