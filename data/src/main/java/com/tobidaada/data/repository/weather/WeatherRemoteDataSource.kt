package com.tobidaada.data.repository.weather

import com.tobidaada.data.models.WeatherData

interface WeatherRemoteDataSource {
    suspend fun getWeather(latitude: Double, longitude: Double): WeatherData
}