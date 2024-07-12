package com.timrashard.weathr.di

import com.timrashard.weathr.data.api.WeatherApi
import com.timrashard.weathr.data.repository.WeatherRepositoryImpl
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
}