package com.tobidaada.domain.entities

data class ForecastEntity(
    val time: Long,
    val minTemperature: Float,
    val maxTemperature: Float,
    val description: String,
    val icon: String
)
