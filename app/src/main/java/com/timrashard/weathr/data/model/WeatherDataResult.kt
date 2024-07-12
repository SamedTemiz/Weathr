package com.timrashard.weathr.data.model

data class WeatherDataResult(
    val queryCost: Int,
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val address: String,
    val timezone: String,
    val tzoffset: Double,
    val description: String,
    val days: List<Day>,
    val alerts: List<Alert>,
    val currentConditions: CurrentConditions,
)