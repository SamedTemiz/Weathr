package com.timrashard.weathr.data.model.air

data class AirPollutionDataResult(
    val coord: Coord,
    val list: List<AirPollutionData>
)