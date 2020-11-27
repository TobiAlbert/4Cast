package com.tobidaada.local.mapper

import com.tobidaada.data.models.ForecastData
import com.tobidaada.local.models.ForecastLocal
import java.util.*
import javax.inject.Inject

class ForecastDataLocalMapper @Inject constructor(): Mapper<ForecastData, ForecastLocal> {
    override fun from(e: ForecastLocal): ForecastData =
        ForecastData(
            time = e.time,
            minTemperature = e.minTemperature,
            maxTemperature = e.maxTemperature,
            description = e.description,
            icon = e.icon,
            createdAt = e.createdAt
        )

    override fun to(t: ForecastData): ForecastLocal =
        ForecastLocal(
            id = UUID.randomUUID().toString(),
            time = t.time,
            minTemperature = t.minTemperature,
            maxTemperature = t.maxTemperature,
            description = t.description,
            icon = t.icon,
            createdAt = t.createdAt
        )
}