package com.tobidaada.data.mapper

import com.tobidaada.data.models.LocationData
import com.tobidaada.domain.entities.LocationEntity

class LocationDomainDataMapper : Mapper<LocationEntity, LocationData> {

    override fun from(e: LocationData): LocationEntity =
        LocationEntity(
            latitude = e.latitude,
            longitude = e.longitude
        )

    override fun to(t: LocationEntity): LocationData =
        LocationData(
            latitude = t.latitude,
            longitude = t.longitude
        )
}