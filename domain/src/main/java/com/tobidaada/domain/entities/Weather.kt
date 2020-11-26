package com.tobidaada.domain.entities

data class CurrentWeatherEntity (
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
