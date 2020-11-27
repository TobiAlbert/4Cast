package com.tobidaada.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tobidaada.local.dao.CurrentWeatherDao
import com.tobidaada.local.dao.ForecastDao
import com.tobidaada.local.models.CurrentWeatherLocal
import com.tobidaada.local.models.ForecastLocal

@Database(entities = [CurrentWeatherLocal::class, ForecastLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun forecastDao(): ForecastDao
}