package com.timrashard.weathr.data.repository

import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.api.WeatherApi
import com.timrashard.weathr.data.model.WeatherDataResult
import com.timrashard.weathr.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override fun getWeatherData(
        latitude: Any,
        longitude: Any,
        apiKey: String
    ): Flow<Resource<WeatherDataResult>> = flow {
        emit(Resource.Loading())

        try {
            val response = weatherApi.getWeatherData(
                latitude = latitude,
                longitude = longitude,
                apiKey = apiKey
            ).awaitResponse()

            if (response.isSuccessful) {
                val weatherData = response.body()
                weatherData?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error("Unknown error"))
            } else {
                emit(Resource.Error("API Error: ${response.code()}"))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Network error: ${e.message}"))
        } catch (e: HttpException) {
            emit(Resource.Error("HTTP error: ${e.message}"))
        }
    }
}

