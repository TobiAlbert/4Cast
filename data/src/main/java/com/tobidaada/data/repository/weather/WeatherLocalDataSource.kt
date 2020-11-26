package com.tobidaada.data.repository.weather

import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.data.models.ForecastData

interface WeatherLocalDataSource {
    suspend fun getCurrentWeather(): CurrentWeatherData?
    suspend fun getWeeklyForecast(): List<ForecastData>
    suspend fun saveCurrentWeather(weather: CurrentWeatherData)
    suspend fun saveWeeklyForecast(weeklyForecast: List<ForecastData>)
}