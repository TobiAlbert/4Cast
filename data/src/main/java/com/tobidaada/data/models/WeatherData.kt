package com.tobidaada.data.models

data class WeatherData(
    val currentWeatherData: CurrentWeatherData,
    val forecastData: List<ForecastData>
)