package com.tobidaada.a4cast.di

import com.tobidaada.a4cast.presentation.mapper.CurrentWeatherMapper
import com.tobidaada.a4cast.presentation.mapper.ForecastMapper
import com.tobidaada.a4cast.presentation.mapper.Mapper
import com.tobidaada.a4cast.presentation.models.CurrentWeather
import com.tobidaada.a4cast.presentation.models.Forecast
import com.tobidaada.domain.entities.CurrentWeatherEntity
import com.tobidaada.domain.entities.ForecastEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindsCurrentWeatherMapperImpl(
        currentWeatherMapper: CurrentWeatherMapper
    ): Mapper<CurrentWeather, CurrentWeatherEntity>

    @Binds
    abstract fun bindsForecastMapperImpl(
        forecastMapper: ForecastMapper
    ): Mapper<Forecast, ForecastEntity>
}
