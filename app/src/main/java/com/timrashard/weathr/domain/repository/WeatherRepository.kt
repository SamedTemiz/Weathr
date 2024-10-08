package com.timrashard.weathr.domain.repository

import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.model.WeatherDataResult
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getWeatherData(latitude: Any, longitude: Any, apiKey: String): Flow<Resource<WeatherDataResult>>
}