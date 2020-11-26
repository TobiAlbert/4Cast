package com.tobidaada.a4cast.di

import com.tobidaada.data.mapper.CurrentWeatherDomainDataMapper
import com.tobidaada.data.mapper.ForecastDomainDataMapper
import com.tobidaada.data.mapper.LocationDomainDataMapper
import com.tobidaada.data.mapper.Mapper
import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.data.models.ForecastData
import com.tobidaada.data.models.LocationData
import com.tobidaada.data.repository.location.LocationRepositoryImpl
import com.tobidaada.data.repository.weather.WeatherRepositoryImpl
import com.tobidaada.domain.entities.CurrentWeatherEntity
import com.tobidaada.domain.entities.ForecastEntity
import com.tobidaada.domain.entities.LocationEntity
import com.tobidaada.domain.repository.location.LocationRepository
import com.tobidaada.domain.repository.weather.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLocationDomainMapper(
        locationDomainDataMapper: LocationDomainDataMapper
    ): Mapper<LocationEntity, LocationData>

    @Binds
    abstract fun bindCurrentWeatherDomainDataMapper(
        currentWeatherDomainDataMapper: CurrentWeatherDomainDataMapper
    ): Mapper<CurrentWeatherEntity, CurrentWeatherData>

    @Binds
    abstract fun bindForecastDomainDataMapper(
        forecastDomainDataMapper: ForecastDomainDataMapper
    ): Mapper<ForecastEntity, ForecastData>

    @Binds
    abstract fun bindsLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    abstract fun bindsWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}