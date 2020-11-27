package com.tobidaada.local.mapper

import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.local.models.CurrentWeatherLocal
import java.util.*
import javax.inject.Inject

class CurrentWeatherDataLocalMapper @Inject constructor(): Mapper<CurrentWeatherData, CurrentWeatherLocal> {
    
    override fun from(e: CurrentWeatherLocal): CurrentWeatherData = 
        CurrentWeatherData(
            dateTime = e.dateTime,
            timezoneOffset = e.timezoneOffset,
            timezone = e.timezone,
            temperature = e.temperature,
            feelsLikeTemperature = e.feelsLikeTemperature,
            humidity = e.humidity,
            ultraVioletIndex = e.ultraVioletIndex,
            windSpeed = e.windSpeed,
            description = e.description,
            icon = e.icon
        )

    override fun to(t: CurrentWeatherData): CurrentWeatherLocal =
        CurrentWeatherLocal(
            id = UUID.randomUUID().toString(),
            dateTime = t.dateTime,
            timezoneOffset = t.timezoneOffset,
            timezone = t.timezone,
            temperature = t.temperature,
            feelsLikeTemperature = t.feelsLikeTemperature,
            humidity = t.humidity,
            ultraVioletIndex = t.ultraVioletIndex,
            windSpeed = t.windSpeed,
            description = t.description,
            icon = t.icon
        )
}