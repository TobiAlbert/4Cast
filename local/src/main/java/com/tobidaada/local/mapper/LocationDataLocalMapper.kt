package com.tobidaada.local.mapper

import com.tobidaada.data.models.LocationData
import com.tobidaada.local.models.LocationLocal
import javax.inject.Inject

class LocationDataLocalMapper @Inject constructor(): Mapper<LocationData, LocationLocal> {
    override fun from(e: LocationLocal): LocationData =
        LocationData(
            latitude = e.latitude,
            longitude = e.longitude
        )

    override fun to(t: LocationData): LocationLocal =
        LocationLocal(
            latitude = t.latitude,
            longitude = t.longitude
        )
}