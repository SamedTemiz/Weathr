package com.timrashard.weathr.data.api

import com.timrashard.weathr.data.model.WeatherDataResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("{latitude},{longitude}/")
    fun getWeatherData(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Query("key") apiKey: String,
        @Query("unitGroup") unitGroup: String = "metric",
        @Query("include") include: String = "days,hours,alerts,current,fcst"
    ): Call<WeatherDataResult>
}