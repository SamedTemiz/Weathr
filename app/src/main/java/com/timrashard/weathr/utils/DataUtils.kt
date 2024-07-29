package com.timrashard.weathr.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.model.WeatherDataResult
import com.timrashard.weathr.data.model.air.AirPollutionDataResult
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.InputStreamReader

object DataUtils {

    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun fetchWeatherDataFromJson(weatherData: MutableStateFlow<Resource<WeatherDataResult>>) {
        try {
            val inputStream = appContext.assets.open("ExampleWeatherData.json")
            val reader = InputStreamReader(inputStream)
            val weatherDataResult: WeatherDataResult = Gson().fromJson(reader, object : TypeToken<WeatherDataResult>() {}.type)
            weatherData.value = Resource.Success(weatherDataResult)
        } catch (e: Exception) {
            weatherData.value = Resource.Error(e.message ?: "An error occurred")
        }
    }

    fun fetchAirPollutionDataFromJson(airData: MutableStateFlow<Resource<AirPollutionDataResult>>) {
        try {
            val inputStream = appContext.assets.open("ExampleAirPollutionData.json")
            val reader = InputStreamReader(inputStream)
            val airDataResult: AirPollutionDataResult = Gson().fromJson(reader, object : TypeToken<AirPollutionDataResult>() {}.type)
            airData.value = Resource.Success(airDataResult)
        } catch (e: Exception) {
            airData.value = Resource.Error(e.message ?: "An error occurred")
        }
    }
}