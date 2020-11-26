package com.tobidaada.a4cast.di

import com.tobidaada.data.mapper.LocationDomainDataMapper
import com.tobidaada.data.mapper.Mapper
import com.tobidaada.data.models.LocationData
import com.tobidaada.data.repository.location.LocationRepositoryImpl
import com.tobidaada.domain.entities.LocationEntity
import com.tobidaada.domain.repository.location.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLocationDomainMapper(
        locationDomainDataMapper: LocationDomainDataMapper
    ): Mapper<LocationEntity, LocationData>

    @Binds
    abstract fun locationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository
}