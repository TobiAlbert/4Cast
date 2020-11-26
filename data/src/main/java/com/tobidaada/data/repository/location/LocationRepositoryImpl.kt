package com.tobidaada.data.repository.location

import com.tobidaada.data.mapper.Mapper
import com.tobidaada.data.models.LocationData
import com.tobidaada.domain.entities.LocationEntity
import com.tobidaada.domain.repository.location.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val locationMapper: Mapper<LocationEntity, LocationData>
) : LocationRepository {

    override fun saveLocation(location: LocationEntity) {
        localDataSource.saveLocationData(locationMapper.to(location))
    }

    override fun getLocation(): LocationEntity {
        return locationMapper.from(localDataSource.getLocationData())
    }
}