package com.tobidaada.a4cast.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tobidaada.a4cast.presentation.mapper.Mapper
import com.tobidaada.a4cast.presentation.models.CurrentWeather
import com.tobidaada.a4cast.presentation.models.Forecast
import com.tobidaada.a4cast.presentation.models.Resource
import com.tobidaada.domain.entities.CurrentWeatherEntity
import com.tobidaada.domain.entities.ForecastEntity
import com.tobidaada.domain.usecases.weather.GetCurrentWeather
import com.tobidaada.domain.usecases.weather.GetWeeklyForecast

class MainViewModel @ViewModelInject constructor(
    private val getCurrentWeather: GetCurrentWeather,
    private val getWeeklyForecast: GetWeeklyForecast,
    private val currentWeatherMapper: Mapper<CurrentWeather, CurrentWeatherEntity>,
    private val forecastMapper: Mapper<Forecast, ForecastEntity>,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    fun getCurrentWeatherData(): LiveData<Resource<CurrentWeather>> = liveData {
        emit(Resource.loading())

        try {
            val response = getCurrentWeather.invoke()
            emit(Resource.success(currentWeatherMapper.from(response)))
        } catch (e: Exception) {
            emit(Resource.error(e.message.toString()))
        }

        emit(Resource.idle())
    }

    fun getForecast(): LiveData<Resource<List<Forecast>>> = liveData {
        emit(Resource.loading())

        try {
            val response = getWeeklyForecast()
            val forecasts = response.map { forecastMapper.from(it) }

            emit(Resource.success(forecasts))
        } catch (e: Exception) {
            emit(Resource.error(e.message.toString()))
        }

        emit(Resource.idle())
    }
}