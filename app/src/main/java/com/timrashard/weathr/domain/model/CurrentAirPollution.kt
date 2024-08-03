package com.timrashard.weathr.domain.model

data class CurrentAirPollution(
    val index: Int,
    val components: EuropeanStandardComponents,
    val dt: Long
)