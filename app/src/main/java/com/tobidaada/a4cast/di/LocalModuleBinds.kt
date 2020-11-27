package com.tobidaada.a4cast.di

import android.content.Context
import androidx.room.Room
import com.tobidaada.data.models.CurrentWeatherData
import com.tobidaada.data.models.ForecastData
import com.tobidaada.data.models.LocationData
import com.tobidaada.data.repository.weather.WeatherLocalDataSource
import com.tobidaada.local.AppDatabase
import com.tobidaada.local.dao.CurrentWeatherDao
import com.tobidaada.local.dao.ForecastDao
import com.tobidaada.local.mapper.CurrentWeatherDataLocalMapper
import com.tobidaada.local.mapper.ForecastDataLocalMapper
import com.tobidaada.local.mapper.LocationDataLocalMapper
import com.tobidaada.local.mapper.Mapper
import com.tobidaada.local.models.CurrentWeatherLocal
import com.tobidaada.local.models.ForecastLocal
import com.tobidaada.local.models.LocationLocal
import com.tobidaada.source.weather.WeatherLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalModuleBinds {

    @Binds
    abstract fun bindLocationDataLocalMapper(
        locationDataLocalMapper: LocationDataLocalMapper
    ): Mapper<LocationData, LocationLocal>

    @Binds
    abstract fun bindWeatherLocalDataSourceImpl(
        weatherLocalDataSource: WeatherLocalDataSourceImpl
    ): WeatherLocalDataSource

    @Binds
    abstract fun bindCurrentWeatherDataLocalMapper(
        currentWeatherDataLocalMapper: CurrentWeatherDataLocalMapper
    ): Mapper<CurrentWeatherData, CurrentWeatherLocal>

    @Binds
    abstract fun bindForecastDataLocalMapper(
        forecastDataLocalMapper: ForecastDataLocalMapper
    ): Mapper<ForecastData, ForecastLocal>

}

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "4cast_weather.db"
        ).build()

    @Provides
    @Singleton
    fun providesCurrentWeatherDao(db: AppDatabase): CurrentWeatherDao = db.currentWeatherDao()

    @Provides
    @Singleton
    fun providesForecastDao(db: AppDatabase): ForecastDao = db.forecastDao()
}