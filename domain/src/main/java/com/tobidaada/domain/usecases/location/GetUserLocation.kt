package com.tobidaada.domain.usecases.location

import com.tobidaada.domain.entities.LocationEntity
import com.tobidaada.domain.repository.location.LocationRepository

class GetUserLocation (private val locationRepository: LocationRepository) {

    operator fun invoke(): LocationEntity {
        return locationRepository.getLocation()
    }
}