package com.tobidaada.a4cast.presentation.mapper

import com.tobidaada.a4cast.presentation.models.CurrentWeather
import com.tobidaada.domain.entities.CurrentWeatherEntity
import javax.inject.Inject

class CurrentWeatherMapper @Inject constructor(): Mapper<CurrentWeather, CurrentWeatherEntity> {
    override fun from(e: CurrentWeatherEntity): CurrentWeather =
        CurrentWeather(
            time = e.time,
            timezone = e.timezone,
            timezoneOffset = e.timezoneOffset,
            temperature = e.temperature,
            feelsLikeTemperature = e.feelsLikeTemperature,
            humidity = e.humidity,
            ultraVioletIndex = e.ultraVioletIndex,
            windSpeed = e.windSpeed,
            description = e.description,
            icon = e.icon
        )

    override fun to(t: CurrentWeather): CurrentWeatherEntity =
        CurrentWeatherEntity(
            time = t.time,
            timezone = t.timezone,
            timezoneOffset = t.timezoneOffset,
            temperature = t.temperature,
            feelsLikeTemperature = t.feelsLikeTemperature,
            humidity = t.humidity,
            ultraVioletIndex = t.ultraVioletIndex,
            windSpeed = t.windSpeed,
            description = t.description,
            icon = t.icon
        )
}