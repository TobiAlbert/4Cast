package com.tobidaada.domain.usecases.weather

import com.tobidaada.domain.entities.ForecastEntity
import com.tobidaada.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class GetWeeklyForecast @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(): List<ForecastEntity> =
        weatherRepository.getWeeklyForecast()
}