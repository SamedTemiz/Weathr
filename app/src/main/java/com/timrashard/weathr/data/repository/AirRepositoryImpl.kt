package com.timrashard.weathr.data.repository

import android.util.Log
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.api.AirPollutionApi
import com.timrashard.weathr.data.model.air.AirPollutionDataResult
import com.timrashard.weathr.domain.repository.AirRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException

class AirRepositoryImpl(
    private val airApi: AirPollutionApi
) : AirRepository {
    override fun getAirPollutionData(
        latitude: Double,
        longitude: Double,
        start: Long,
        end: Long,
        apiKey: String
    ): Flow<Resource<AirPollutionDataResult>> = flow {
        emit(Resource.Loading())

        try {
            val response = withContext(Dispatchers.IO) {
                airApi.getAirPollutionHistory(
                    latitude = latitude,
                    longitude = longitude,
                    start = start,
                    end = end,
                    apiKey = apiKey
                ).awaitResponse()
            }

            Log.d("API_RESPONSE_RAW", response.raw().toString()) // Yanıtın ham halini logla
            Log.d("API_RESPONSE_BODY", response.body().toString()) // Yanıt gövdesini logla

            if (response.isSuccessful) {
                val airPollutionData = response.body()

                airPollutionData?.let {
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
