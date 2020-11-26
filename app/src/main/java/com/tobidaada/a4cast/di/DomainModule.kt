package com.tobidaada.a4cast.di

import com.tobidaada.data.repository.location.LocalDataSource
import com.tobidaada.source.location.LocationDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindLocationDataSource(
        locationDatSourceImpl: LocationDataSourceImpl
    ): LocalDataSource
}