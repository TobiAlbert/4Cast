package com.tobidaada.a4cast.presentation.models

data class Forecast (
    val time: Long,
    val minTemperature: Float,
    val maxTemperature: Float,
    val description: String,
    val icon: String
)