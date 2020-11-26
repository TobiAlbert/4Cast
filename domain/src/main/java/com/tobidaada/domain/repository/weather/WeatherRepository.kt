package com.tobidaada.domain.repository.weather

import com.tobidaada.domain.entities.CurrentWeatherEntity
import com.tobidaada.domain.entities.ForecastEntity

interface WeatherRepository {
    suspend fun getCurrentWeather(): CurrentWeatherEntity
    suspend fun getWeeklyForecast(): List<ForecastEntity>
    suspend fun saveCurrentWeather(weather:  CurrentWeatherEntity)
    suspend fun saveWeeklyForecast(weeklyForecast: List<ForecastEntity>)
}