package com.timrashard.weathr.domain.repository

import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.model.air.AirPollutionDataResult
import kotlinx.coroutines.flow.Flow

interface AirRepository {

    fun getAirPollutionData(
        latitude: Double,
        longitude: Double,
        start: Long,
        end: Long,
        apiKey: String
    ): Flow<Resource<AirPollutionDataResult>>
}