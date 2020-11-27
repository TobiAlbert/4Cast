package com.tobidaada.a4cast.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import com.tobidaada.a4cast.BuildConfig
import com.tobidaada.data.models.WeatherData
import com.tobidaada.data.repository.weather.WeatherRemoteDataSource
import com.tobidaada.remote.mapper.Mapper
import com.tobidaada.remote.mapper.WeatherDataRemoteMapper
import com.tobidaada.remote.models.WeatherResponse
import com.tobidaada.remote.services.WeatherService
import com.tobidaada.remote.source.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun networkInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val originalHttpUrl = request.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()

        val builder = request.newBuilder()
            .addHeader("Accept-Language", "en-US")
            .addHeader("Content-Type", "application/json")
            .url(url)
            .build()

        return@Interceptor chain.proceed(builder)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(ChuckInterceptor(context))
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteModuleBinds {
    @Binds
    abstract fun bindsWeatherRemoteMapper(
        weatherDataRemoteMapper: WeatherDataRemoteMapper
    ): Mapper<WeatherData, WeatherResponse>

    @Binds
    abstract fun bindsWeatherRemoteDataSourceImpl(
        weatherRemoteDataSourceImpl: WeatherRemoteDataSourceImpl
    ): WeatherRemoteDataSource
}