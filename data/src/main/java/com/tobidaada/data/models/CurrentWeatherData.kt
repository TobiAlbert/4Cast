package com.tobidaada.data.models

data class CurrentWeatherData (
    val time: Long,
    val sunriseTime: Long,
    val sunsetTime: Long,
    val temperature: Float,
    val feelsLikeTemperature: Float,
    val humidity: Int,
    val ultraVioletIndex: Float,
    val windSpeed: Float,
    val description: String,
    val icon: String
)