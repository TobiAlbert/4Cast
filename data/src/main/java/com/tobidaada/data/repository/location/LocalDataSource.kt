package com.tobidaada.data.repository.location

import com.tobidaada.data.models.LocationData

interface LocalDataSource {
    fun saveLocationData(location: LocationData)
    fun getLocationData(): LocationData
}