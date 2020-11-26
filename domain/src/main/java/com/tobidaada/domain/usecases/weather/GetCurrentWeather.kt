package com.tobidaada.domain.usecases.weather

import com.tobidaada.domain.entities.CurrentWeatherEntity
import com.tobidaada.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class GetCurrentWeather @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(): CurrentWeatherEntity = weatherRepository.getCurrentWeather()
}