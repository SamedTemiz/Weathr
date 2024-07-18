package com.timrashard.weathr.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherIconApi {

    @GET("Weathr/master/WeatherIcons/{iconName}.png")
    suspend fun getIcon(@Path("iconName") iconName: String): String
}