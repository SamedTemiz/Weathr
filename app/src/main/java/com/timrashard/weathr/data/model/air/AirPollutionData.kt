package com.timrashard.weathr.data.model.air

data class AirPollutionData(
    val main: AirQualityIndex,
    val components: Components,
    val dt: Long,
)