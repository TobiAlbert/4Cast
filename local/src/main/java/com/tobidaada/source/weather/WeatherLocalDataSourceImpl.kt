package com.tobidaada.source.weather

import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.data.models.ForecastData
import com.tobidaada.data.repository.weather.WeatherLocalDataSource
import com.tobidaada.local.dao.CurrentWeatherDao
import com.tobidaada.local.dao.ForecastDao
import com.tobidaada.local.mapper.CurrentWeatherDataLocalMapper
import com.tobidaada.local.mapper.ForecastDataLocalMapper
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(
    private val currentWeatherDao: CurrentWeatherDao,
    private val forecastDao: ForecastDao,
    private val currentWeatherDataLocalMapper: CurrentWeatherDataLocalMapper,
    private val forecastDataLocalMapper: ForecastDataLocalMapper
): WeatherLocalDataSource {

    override suspend fun getCurrentWeather(): CurrentWeatherData? {
        val weather = currentWeatherDao.get() ?: return null
        return currentWeatherDataLocalMapper.from(weather)
    }

    override suspend fun getWeeklyForecast(): List<ForecastData> =
        forecastDao.getAll().map { forecastDataLocalMapper.from(it) }

    override suspend fun saveCurrentWeather(weather: CurrentWeatherData) {
        // clear previous data, and update with new value
        currentWeatherDao.deleteAll()
        currentWeatherDao.add(currentWeatherDataLocalMapper.to(weather))
    }

    override suspend fun saveWeeklyForecast(weeklyForecast: List<ForecastData>) {
        // clear existing data, and update with new value
        forecastDao.deleteAll()
        forecastDao.addAll(weeklyForecast.map { forecastDataLocalMapper.to(it) })
    }
}