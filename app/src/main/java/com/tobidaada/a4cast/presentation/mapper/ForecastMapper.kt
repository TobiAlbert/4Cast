package com.tobidaada.a4cast.presentation.mapper

import com.tobidaada.a4cast.presentation.models.Forecast
import com.tobidaada.domain.entities.ForecastEntity
import javax.inject.Inject

class ForecastMapper @Inject constructor(): Mapper<Forecast, ForecastEntity> {
    override fun from(e: ForecastEntity): Forecast = 
        Forecast(
            time = e.time,
            minTemperature = e.minTemperature,
            maxTemperature = e.maxTemperature,
            description = e.description,
            icon = e.icon
        )
    override fun to(t: Forecast): ForecastEntity =
        ForecastEntity(
            time = t.time,
            minTemperature = t.minTemperature,
            maxTemperature = t.maxTemperature,
            description = t.description,
            icon = t.icon
        )
}