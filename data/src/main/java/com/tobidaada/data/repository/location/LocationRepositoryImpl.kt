package com.tobidaada.data.repository.location

import com.tobidaada.data.mapper.LocationDomainDataMapper
import com.tobidaada.domain.entities.LocationEntity
import com.tobidaada.domain.repository.location.LocationRepository

class LocationRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val locationMapper: LocationDomainDataMapper
) : LocationRepository {

    override fun saveLocation(location: LocationEntity) {
        localDataSource.saveLocationData(locationMapper.to(location))
    }

    override fun getLocation(): LocationEntity {
        return locationMapper.from(localDataSource.getLocationData())
    }
}