package com.tobidaada.data.mapper

import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.domain.entities.CurrentWeatherEntity
import javax.inject.Inject

class CurrentWeatherDomainDataMapper @Inject constructor(): Mapper<CurrentWeatherEntity, CurrentWeatherData> {
    override fun from(e: CurrentWeatherData): CurrentWeatherEntity =
        CurrentWeatherEntity(
            time = e.time,
            sunriseTime = e.sunriseTime,
            sunsetTime = e.sunsetTime,
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
            time = t.time,
            sunriseTime = t.sunriseTime,
            sunsetTime = t.sunsetTime,
            temperature = t.temperature,
            feelsLikeTemperature = t.feelsLikeTemperature,
            humidity = t.humidity,
            ultraVioletIndex = t.ultraVioletIndex,
            windSpeed = t.windSpeed,
            description = t.description,
            icon = t.icon
        )
}