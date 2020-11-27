package com.tobidaada.a4cast.presentation.models

data class CurrentWeather (
    val time: Long,
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