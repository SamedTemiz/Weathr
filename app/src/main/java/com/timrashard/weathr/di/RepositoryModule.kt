package com.timrashard.weathr.di

import android.content.Context
import com.timrashard.weathr.data.api.AirPollutionApi
import com.timrashard.weathr.data.api.WeatherApi
import com.timrashard.weathr.data.repository.AirRepositoryImpl
import com.timrashard.weathr.data.repository.ConnectivityRepositoryImpl
import com.timrashard.weathr.data.repository.WeatherRepositoryImpl
import com.timrashard.weathr.domain.repository.AirRepository
import com.timrashard.weathr.domain.repository.ConnectivityRepository
import com.timrashard.weathr.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }

    @Provides
    @Singleton
    fun provideAirRepository(airApi: AirPollutionApi): AirRepository {
        return AirRepositoryImpl(airApi)
    }

    @Provides
    @Singleton
    fun provideConnectivityRepository(context: Context): ConnectivityRepository {
        return ConnectivityRepositoryImpl(context)
    }
}