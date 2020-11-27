package com.tobidaada.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tobidaada.local.models.ForecastLocal

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(forecasts: List<ForecastLocal>)

    @Query("SELECT * FROM weekly_forecast")
    suspend fun getAll(): List<ForecastLocal>

    @Query("DELETE FROM weekly_forecast")
    suspend fun deleteAll()
}