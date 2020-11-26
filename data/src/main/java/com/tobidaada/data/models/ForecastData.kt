package com.tobidaada.data.models

data class ForecastData(
    val time: Long,
    val minTemperature: Float,
    val maxTemperature: Float,
    val description: String,
    val icon: String,
    val createdAt: Long
)