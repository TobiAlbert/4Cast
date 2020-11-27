package com.tobidaada.data.repository.weather

import com.tobidaada.data.CACHE_EXPIRATION
import com.tobidaada.data.mapper.Mapper
import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.data.models.ForecastData
import com.tobidaada.domain.entities.CurrentWeatherEntity
import com.tobidaada.domain.entities.ForecastEntity
import com.tobidaada.domain.repository.weather.WeatherRepository
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val localDataSource: WeatherLocalDataSource,
    private val remoteDataSource: WeatherRemoteDataSource,
    private val currentWeatherMapper: Mapper<CurrentWeatherEntity, CurrentWeatherData>,
    private val forecastWeatherMapper: Mapper<ForecastEntity, ForecastData>
) : WeatherRepository {

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeatherEntity {
        val currentWeather = localDataSource.getCurrentWeather()
        if (currentWeather != null) {
            val currentTime = System.currentTimeMillis()
            val createdAt = currentWeather.dateTime
            val hasExpired =
                ((currentTime - createdAt) > CACHE_EXPIRATION) || !isSameDay(currentTime, createdAt)
            if (!hasExpired) {
                return currentWeatherMapper.from(currentWeather)
            }
        }

        // fetch from remote data source, and save.
        val weatherData = remoteDataSource.getWeather(latitude, longitude)
        saveWeeklyForecast(weatherData.forecastData.map { forecastWeatherMapper.from(it) })
        return currentWeatherMapper.from(weatherData.currentWeatherData).also { saveCurrentWeather(it) }
    }

    override suspend fun getWeeklyForecast(latitude: Double, longitude: Double): List<ForecastEntity> {
        val currentForecast = localDataSource.getWeeklyForecast()

        if (currentForecast.isNotEmpty()) {
            val forecast: ForecastData = currentForecast.last()
            // if you're in a new day or cache has expired, then get a new weekly forecast
            val currentTime = System.currentTimeMillis()
            val createdAt = forecast.createdAt
            val hasExpired =
                ((currentTime - createdAt) > CACHE_EXPIRATION) || !isSameDay(currentTime, createdAt)

            if (!hasExpired) {
                return currentForecast.map { forecastWeatherMapper.from(it) }
            }
        }

        val weatherData = remoteDataSource.getWeather(latitude, longitude)
        saveCurrentWeather(currentWeatherMapper.from(weatherData.currentWeatherData))
        return weatherData.forecastData.map { forecastWeatherMapper.from(it) }.also { saveWeeklyForecast(it) }
    }

    override suspend fun saveCurrentWeather(weather: CurrentWeatherEntity) {
        val weatherData: CurrentWeatherData = currentWeatherMapper.to(weather)
        localDataSource.saveCurrentWeather(weatherData)
    }

    override suspend fun saveWeeklyForecast(weeklyForecast: List<ForecastEntity>) {
        val forecastData: List<ForecastData> = weeklyForecast.map { forecastWeatherMapper.to(it) }
        localDataSource.saveWeeklyForecast(forecastData)
    }

    private fun isSameDay(currentDate: Long, otherDate: Long): Boolean {
        val currentCalendarTime = Calendar.getInstance()
        val otherCalendarTime = Calendar.getInstance()

        currentCalendarTime.time = Date(currentDate)
        otherCalendarTime.time = Date(otherDate)

        return (currentCalendarTime.get(Calendar.DAY_OF_YEAR) == otherCalendarTime.get(Calendar.DAY_OF_YEAR)) &&
                (currentCalendarTime.get(Calendar.YEAR) == otherCalendarTime.get(Calendar.YEAR))
    }
}