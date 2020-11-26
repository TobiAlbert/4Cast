package com.tobidaada.data.repository.weather

import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.data.models.ForecastData

interface WeatherRemoteDataSource {
    suspend fun getCurrentWeather(): CurrentWeatherData
    suspend fun getWeeklyForecast(): List<ForecastData>
}