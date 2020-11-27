package com.tobidaada.local.dao

import androidx.room.*
import com.tobidaada.local.models.CurrentWeatherLocal

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(currentWeather: CurrentWeatherLocal)

    @Query("SELECT * FROM current_weather LIMIT 1")
    suspend fun get(): CurrentWeatherLocal?

    @Query("DELETE FROM current_weather")
    suspend fun deleteAll()

}