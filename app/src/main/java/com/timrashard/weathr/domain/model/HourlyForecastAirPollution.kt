package com.timrashard.weathr.domain.model

import com.timrashard.weathr.data.model.air.AirPollutionData

data class HourlyForecastAirPollution(
    val list: List<AirPollutionData>
)
