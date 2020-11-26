package com.tobidaada.data.mapper

import com.tobidaada.data.models.ForecastData
import com.tobidaada.domain.entities.ForecastEntity
import javax.inject.Inject

class ForecastDomainDataMapper @Inject constructor(): Mapper<ForecastEntity, ForecastData> {

    override fun from(e: ForecastData): ForecastEntity =
        ForecastEntity(
            time = e.time,
            minTemperature = e.minTemperature,
            maxTemperature = e.maxTemperature,
            description = e.description,
            icon = e.icon
        )

    override fun to(t: ForecastEntity): ForecastData =
        ForecastData(
            time = t.time,
            minTemperature = t.minTemperature,
            maxTemperature = t.maxTemperature,
            description = t.description,
            icon = t.icon,
            createdAt = System.currentTimeMillis()
        )
}