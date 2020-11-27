package com.tobidaada.domain.repository.weather

import com.tobidaada.domain.entities.CurrentWeatherEntity
import com.tobidaada.domain.entities.ForecastEntity

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeatherEntity
    suspend fun getWeeklyForecast(latitude: Double, longitude: Double): List<ForecastEntity>
    suspend fun saveCurrentWeather(weather:  CurrentWeatherEntity)
    suspend fun saveWeeklyForecast(weeklyForecast: List<ForecastEntity>)
}