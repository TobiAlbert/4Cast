package com.tobidaada.source.weather

import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.data.models.ForecastData
import com.tobidaada.data.repository.weather.WeatherLocalDataSource
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(): WeatherLocalDataSource {
    override suspend fun getCurrentWeather(): CurrentWeatherData? = null

    override suspend fun getWeeklyForecast(): List<ForecastData> = emptyList()

    override suspend fun saveCurrentWeather(weather: CurrentWeatherData) = Unit

    override suspend fun saveWeeklyForecast(weeklyForecast: List<ForecastData>) = Unit
}