package com.tobidaada.domain.repository.location

import com.tobidaada.domain.entities.LocationEntity

interface LocationRepository {
    fun saveLocation(location: LocationEntity)
    fun getLocation(): LocationEntity
}