package com.tobidaada.remote.services

import com.tobidaada.remote.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String = "hourly",
        @Query("units") units: String = "metric"
    ): WeatherResponse
}