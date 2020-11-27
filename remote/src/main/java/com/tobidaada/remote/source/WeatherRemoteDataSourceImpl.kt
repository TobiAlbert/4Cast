package com.tobidaada.remote.source

import com.tobidaada.data.models.WeatherData
import com.tobidaada.data.repository.weather.WeatherRemoteDataSource
import com.tobidaada.remote.mapper.Mapper
import com.tobidaada.remote.models.WeatherResponse
import com.tobidaada.remote.services.WeatherService
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherMapper: Mapper<WeatherData, WeatherResponse>
): WeatherRemoteDataSource {

    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherData =
        weatherMapper.from(weatherService.getWeather(latitude = latitude, longitude = longitude))
}