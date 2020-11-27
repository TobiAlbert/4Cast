package com.tobidaada.data.models

data class CurrentWeatherData (
    val dateTime: Long,
    val timezoneOffset: Int,
    val timezone: String,
    val temperature: Float,
    val feelsLikeTemperature: Float,
    val humidity: Int,
    val ultraVioletIndex: Float,
    val windSpeed: Float,
    val description: String,
    val icon: String
)