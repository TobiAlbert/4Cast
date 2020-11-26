package com.tobidaada.domain.usecases.location

import com.tobidaada.domain.entities.LocationEntity
import com.tobidaada.domain.repository.location.LocationRepository
import javax.inject.Inject

class GetUserLocation @Inject constructor(private val locationRepository: LocationRepository) {

    operator fun invoke(): LocationEntity {
        return locationRepository.getLocation()
    }
}