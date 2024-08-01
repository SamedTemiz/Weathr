package com.timrashard.weathr.domain.model

data class CurrentAirPollution(
    val index: Int,
    val components: EuropeanStandard,
    val dt: Long
)