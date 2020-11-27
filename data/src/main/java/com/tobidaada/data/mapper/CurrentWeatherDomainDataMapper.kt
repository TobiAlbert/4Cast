package com.tobidaada.data.mapper

import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.domain.entities.CurrentWeatherEntity
import javax.inject.Inject

class CurrentWeatherDomainDataMapper @Inject constructor(): Mapper<CurrentWeatherEntity, CurrentWeatherData> {
    override fun from(e: CurrentWeatherData): CurrentWeatherEntity =
        CurrentWeatherEntity(
            time = e.dateTime,
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

    override fun to(t: CurrentWeatherEntity): CurrentWeatherData =
        CurrentWeatherData(
            dateTime = t.time,
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