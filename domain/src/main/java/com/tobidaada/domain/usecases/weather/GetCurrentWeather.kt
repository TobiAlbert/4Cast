package com.tobidaada.domain.usecases.weather

import com.tobidaada.domain.entities.CurrentWeatherEntity
import com.tobidaada.domain.repository.location.LocationRepository
import com.tobidaada.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class GetCurrentWeather @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) {

    suspend operator fun invoke(): CurrentWeatherEntity {
        val location = locationRepository.getLocation()
        return weatherRepository.getCurrentWeather(location.latitude, location.longitude)
    }
}