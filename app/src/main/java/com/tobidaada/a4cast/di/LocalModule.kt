package com.tobidaada.a4cast.di

import com.tobidaada.data.models.LocationData
import com.tobidaada.local.mapper.LocationDataLocalMapper
import com.tobidaada.local.mapper.Mapper
import com.tobidaada.local.models.LocationLocal
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalModule {

    @Binds
    abstract fun bindLocationDataLocalMapper(
        locationDataLocalMapper: LocationDataLocalMapper
    ): Mapper<LocationData, LocationLocal>
}