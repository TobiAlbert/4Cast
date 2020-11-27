package com.tobidaada.remote.mapper

import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.data.models.ForecastData
import com.tobidaada.data.models.WeatherData
import com.tobidaada.remote.models.*
import javax.inject.Inject

class WeatherDataRemoteMapper @Inject constructor(): Mapper<WeatherData, WeatherResponse> {

    override fun from(e: WeatherResponse): WeatherData {
        val currentWeatherData = CurrentWeatherData(
            dateTime = e.current.dateTime,
            timezoneOffset = e.timezoneOffset,
            timezone = e.timezone,
            temperature = e.current.temperature,
            feelsLikeTemperature = e.current.feelsLikeTemperature,
            humidity = e.current.humidity,
            ultraVioletIndex = e.current.ultraVioletIndex,
            windSpeed = e.current.windSpeed,
            description = e.current.weather.first().description,
            icon = e.current.weather.first().icon,
            createdAt = System.currentTimeMillis()
        )

        val forecast = e.daily.map {
            return@map ForecastData(
                time = it.dateTime,
                minTemperature = it.temp.minDailyTemp,
                maxTemperature = it.temp.maxDailyTemp,
                description = it.weather.first().description,
                icon = it.weather.first().icon,
                createdAt = System.currentTimeMillis()
            )
        }

        return WeatherData(currentWeatherData, forecast)
    }

    override fun to(t: WeatherData): WeatherResponse {
        val currentWeatherRemote = CurrentWeatherRemote(
            dateTime = t.currentWeatherData.dateTime,
            temperature = t.currentWeatherData.temperature,
            feelsLikeTemperature = t.currentWeatherData.feelsLikeTemperature,
            humidity = t.currentWeatherData.humidity,
            ultraVioletIndex = t.currentWeatherData.ultraVioletIndex,
            windSpeed = t.currentWeatherData.windSpeed,
            weather = listOf(Weather(t.currentWeatherData.description, t.currentWeatherData.icon))
        )

        val weeklyForecast = t.forecastData.map {
            return@map WeeklyForecast(
                dateTime = it.time,
                weather = listOf(Weather(it.description, it.icon)),
                temp = Temp(minDailyTemp = it.minTemperature, maxDailyTemp = it.maxTemperature)
            )
        }

        return WeatherResponse(
            timezone = t.currentWeatherData.timezone,
            timezoneOffset = t.currentWeatherData.timezoneOffset,
            current = currentWeatherRemote,
            daily = weeklyForecast
        )
    }
}