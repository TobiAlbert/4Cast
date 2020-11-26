package com.tobidaada.source.location

import com.tobidaada.data.models.LocationData
import com.tobidaada.data.repository.location.LocalDataSource
import javax.inject.Inject

class LocationDataSourceImpl @Inject constructor(private val sharedPreferenceManager: SharedPreferenceManager): LocalDataSource {

    override fun saveLocationData(location: LocationData) {
        sharedPreferenceManager.saveDouble(SharedPreferenceManager.LATITUDE, location.latitude)
        sharedPreferenceManager.saveDouble(SharedPreferenceManager.LONGITUDE, location.longitude)
    }

    override fun getLocationData(): LocationData {
        val latitude: Double = sharedPreferenceManager.getDouble(SharedPreferenceManager.LATITUDE)
        val longitude: Double = sharedPreferenceManager.getDouble(SharedPreferenceManager.LONGITUDE)

        return LocationData(latitude = latitude, longitude = longitude)
    }
}