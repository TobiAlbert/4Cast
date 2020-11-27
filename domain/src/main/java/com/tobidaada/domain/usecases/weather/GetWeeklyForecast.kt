package com.tobidaada.domain.usecases.weather

import com.tobidaada.domain.entities.ForecastEntity
import com.tobidaada.domain.repository.location.LocationRepository
import com.tobidaada.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class GetWeeklyForecast @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): List<ForecastEntity> =
        locationRepository.getLocation().run { weatherRepository.getWeeklyForecast(latitude, longitude) }
}