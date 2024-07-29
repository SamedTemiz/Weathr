package com.timrashard.weathr.data.api

import com.timrashard.weathr.data.model.air.AirPollutionDataResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AirPollutionApi {

    @GET("data/2.5/air_pollution/history")
    fun getAirPollutionHistory(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("start") start: Long,
        @Query("end") end: Long,
        @Query("appid") apiKey: String
    ): Call<AirPollutionDataResult>
}