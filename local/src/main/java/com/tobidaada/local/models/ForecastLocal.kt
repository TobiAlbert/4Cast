package com.tobidaada.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weekly_forecast")
data class ForecastLocal(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "time")
    val time: Long,

    @ColumnInfo(name = "min_temperature")
    val minTemperature: Float,

    @ColumnInfo(name = "max_temperature")
    val maxTemperature: Float,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "icon")
    val icon: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
)