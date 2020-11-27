package com.tobidaada.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeatherLocal (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "date_time")
    val dateTime: Long,

    @ColumnInfo(name = "timezone_offset")
    val timezoneOffset: Int,

    @ColumnInfo(name = "timezone")
    val timezone: String,

    @ColumnInfo(name = "temperature")
    val temperature: Float,

    @ColumnInfo(name = "feels_like_temperature")
    val feelsLikeTemperature: Float,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "ultra_violet_index")
    val ultraVioletIndex: Float,

    @ColumnInfo(name = "wind_speed")
    val windSpeed: Float,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "icon")
    val icon: String
)