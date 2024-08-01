package com.timrashard.weathr.data.model.air

import com.timrashard.weathr.domain.model.CurrentAirPollution
import com.timrashard.weathr.domain.model.HourlyForecastAirPollution

data class AirPollutionDataResult(
    val coord: Coord,
    val list: List<AirPollutionData>
)

fun AirPollutionDataResult.toCurrentAirPollution() : CurrentAirPollution {
    return CurrentAirPollution(
        index = list.first().main.aqi,
        components = list.first().components.toEuropeanStandard(),
        dt = list.first().dt
    )
}

fun AirPollutionDataResult.toHourlyForecastAirPollution() : HourlyForecastAirPollution {
    return HourlyForecastAirPollution(
        list = this.list
    )
}